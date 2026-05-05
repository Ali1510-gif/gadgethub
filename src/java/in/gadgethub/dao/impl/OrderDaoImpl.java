package in.gadgethub.dao.impl;

import in.gadgethub.dao.OrderDao;
import in.gadgethub.dao.ProductDao;
import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.*;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IDUtil;
import in.gadgethub.utility.MailMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean addOrders(OrderPojo order) {
        String sql = "Insert into orders values(?,?,?,?,?)";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getProdId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getAmount());
            ps.setInt(5, 0);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addTransaction(TransactionPojo transaction) {
        String sql = "Insert into transactions values(?,?,?,?)";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transaction.getTransId());
            ps.setString(2, transaction.getUserEmail());
            ps.setDate(3, new java.sql.Date(transaction.getTransTime().getTime()));
            ps.setDouble(4, transaction.getAmount());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderPojo> getAllOrders() {
        List<OrderPojo> list = new ArrayList<>();

        String sql = "SELECT o.*, u.useremail, u.address FROM orders o " +
                     "LEFT JOIN transactions t ON t.transid = o.orderid " +
                     "LEFT JOIN users u ON u.useremail = t.useremail";

        try (Connection conn = DBUtil.provideConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProdId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                order.setUseremail(rs.getString("useremail"));
                order.setAddress(rs.getString("address"));
                list.add(order);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public List<OrderDetailsPojo> getAllOrderDetails(String userEmailId) {
        List<OrderDetailsPojo> list = new ArrayList<>();

        String sql = "Select p.pid as prodid,o.orderid as orderid,o.shipped as shipped," +
                "p.image as image,p.pname as pname,o.quantity as qty,o.amount as amount," +
                "t.transtime as time From orders o,products p,transactions t " +
                "where o.orderid=t.transid and o.prodid=p.pid and t.useremail=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userEmailId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetailsPojo o = new OrderDetailsPojo();
                    o.setOrderId(rs.getString("orderid"));
                    o.setProdId(rs.getString("prodid"));
                    o.setProdName(rs.getString("pname"));
                    o.setQuantity(rs.getInt("qty"));
                    o.setAmount(rs.getDouble("amount"));
                    o.setShipped(rs.getInt("shipped"));
                    o.setTime(rs.getTimestamp("time"));
                    o.setProdImage(rs.getAsciiStream("image"));
                    list.add(o);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public String shipNow(String orderId, String prodId) {
        String status = "Failure!";

        String sql = "update orders set shipped=1 where orderid=? and prodid=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ps.setString(2, prodId);

            if (ps.executeUpdate() > 0) {

                status = "Order shipped successfully";

                try {
                    String userEmail = "rajneeshkushwaha3757@gmail.com";

                    UserDao userDao = new UserDaoImpl();
                    String userName = userDao.getUserFirstName(userEmail);

                    ProductDao productDao = new ProductDaoImpl();
                    ProductPojo product = productDao.getProductDetails(prodId);

                    MailMessage.orderShippedSuccess(
                            userEmail,
                            userName,
                            product.getProdName(),
                            product.getProdQuantity(),
                            orderId
                    );

                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return status;
    }

    @Override
    public String paymentSuccess(String username, double paidAmount) {
        String status = "Order Placement Failed!!";

        try (Connection conn = DBUtil.provideConnection()) {

            conn.setAutoCommit(false); // ⚠️ manual TX

            CartDaoImpl cartDao = new CartDaoImpl();
            List<UsercartPojo> cartList = cartDao.getAllCartItems(username);

            if (cartList.isEmpty()) return status;

            String transactionId = IDUtil.generateTransId();

            try (PreparedStatement psTrans =
                         conn.prepareStatement("INSERT INTO transactions VALUES(?,?,?,?)");
                 PreparedStatement psOrder =
                         conn.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?)")) {

                // insert transaction
                psTrans.setString(1, transactionId);
                psTrans.setString(2, username);
                psTrans.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                psTrans.setDouble(4, paidAmount);

                if (psTrans.executeUpdate() <= 0) {
                    conn.rollback();
                    return status;
                }

                ProductDaoImpl productDAO = new ProductDaoImpl();
                boolean ordered = true;

                for (UsercartPojo cart : cartList) {

                    double amount = productDAO.getProductPrice(cart.getProdId()) * cart.getQuantity();

                    psOrder.setString(1, transactionId);
                    psOrder.setString(2, cart.getProdId());
                    psOrder.setInt(3, cart.getQuantity());
                    psOrder.setDouble(4, amount);
                    psOrder.setInt(5, 0);

                    if (psOrder.executeUpdate() <= 0 ||
                        !cartDao.removeAProduct(cart.getUserEmail(), cart.getProdId()) ||
                        !productDAO.sellNProduct(cart.getProdId(), cart.getQuantity())) {

                        ordered = false;
                        break;
                    }
                }

                if (ordered) {
                    conn.commit();
                    status = "Order Placed Successfully!";

                    try {
                        UserDao userDao = new UserDaoImpl();
                        String userName = userDao.getUserFirstName(username);

                        MailMessage.orderPlacedSuccess(username, userName, 0, transactionId);

                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }

                } else {
                    conn.rollback();
                }

            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true); // restore
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public int getSoldQuantity(String prodId) {
        String sql = "select sum(quantity) from orders where prodid=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}