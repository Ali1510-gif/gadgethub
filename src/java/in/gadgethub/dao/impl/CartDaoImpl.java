package in.gadgethub.dao.impl;

import in.gadgethub.dao.CartDao;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.pojo.UsercartPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public String addProductToCart(UsercartPojo cart) {
        String status = "Failed to add into cart!";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "select * from usercart where useremail=? and prodid=?")) {

            ps.setString(1, cart.getUserEmail());
            ps.setString(2, cart.getProdId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductDaoImpl prodDao = new ProductDaoImpl();
                    int stockQty = prodDao.getProductQuantity(cart.getProdId());
                    int newQty = cart.getQuantity() + rs.getInt("quantity");

                    if (stockQty < newQty) {
                        cart.setQuantity(stockQty);
                        updateProductInCart(cart);

                        DemandPojo demandPojo = new DemandPojo();
                        demandPojo.setProdId(cart.getProdId());
                        demandPojo.setUserEmail(cart.getUserEmail());
                        demandPojo.setQuantity(newQty - stockQty);

                        DemandDaoImpl demandDao = new DemandDaoImpl();
                        if (demandDao.addProduct(demandPojo)) {
                            status = "We will mail you when " + (newQty - stockQty) + " items are available";
                        }
                    } else {
                        cart.setQuantity(newQty);
                        updateProductInCart(cart);
                    }
                }
            }

        } catch (SQLException ex) {
            status = "Update Failed into cart!";
            ex.printStackTrace();
        }

        return status;
    }

    @Override
    public String updateProductInCart(UsercartPojo cart) {
        String status = "Failed to Add into cart!";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps1 = conn.prepareStatement(
                     "select * from usercart where prodid=? and useremail=?")) {

            ps1.setString(1, cart.getProdId());
            ps1.setString(2, cart.getUserEmail());

            try (ResultSet rs = ps1.executeQuery()) {
                if (rs.next()) {
                    int qty = cart.getQuantity();

                    if (qty > 0) {
                        try (PreparedStatement ps2 = conn.prepareStatement(
                                "update usercart set quantity=? where prodid=? and useremail=?")) {

                            ps2.setInt(1, qty);
                            ps2.setString(2, cart.getProdId());
                            ps2.setString(3, cart.getUserEmail());

                            if (ps2.executeUpdate() > 0) {
                                status = "Product Successfully Updated to Cart!";
                            }
                        }
                    } else if (qty == 0) {
                        try (PreparedStatement ps2 = conn.prepareStatement(
                                "delete from usercart where prodid=? and useremail=?")) {

                            ps2.setString(1, cart.getProdId());
                            ps2.setString(2, cart.getUserEmail());

                            if (ps2.executeUpdate() > 0) {
                                status = "Product Successfully Updated in Cart!";
                            }
                        }
                    }
                } else {
                    try (PreparedStatement ps2 = conn.prepareStatement(
                            "insert into usercart values(?,?,?)")) {

                        ps2.setString(1, cart.getUserEmail());
                        ps2.setString(2, cart.getProdId());
                        ps2.setInt(3, cart.getQuantity());

                        if (ps2.executeUpdate() > 0) {
                            status = "Product Successfully Added to Cart!";
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            status = "Update Failed into cart!";
            ex.printStackTrace();
        }

        return status;
    }

    @Override
    public List<UsercartPojo> getAllCartItems(String userId) {
        List<UsercartPojo> list = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "select * from usercart where useremail=?")) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsercartPojo cart = new UsercartPojo();
                    cart.setUserEmail(rs.getString("useremail"));
                    cart.setProdId(rs.getString("prodid"));
                    cart.setQuantity(rs.getInt("quantity"));
                    list.add(cart);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public int getCartItemCount(String userId, String itemId) {
        if (userId == null || itemId == null) return 0;

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "select quantity from usercart where useremail=? and prodid=?")) {

            ps.setString(1, userId);
            ps.setString(2, itemId);

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

    @Override
    public String removeProductFromCart(String userId, String prodId) {
        String status = "Product removal failed!";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps1 = conn.prepareStatement(
                     "select * from usercart where useremail=? and prodid=?")) {

            ps1.setString(1, userId);
            ps1.setString(2, prodId);

            try (ResultSet rs = ps1.executeQuery()) {
                if (rs.next()) {
                    int qty = rs.getInt("quantity") - 1;

                    if (qty > 0) {
                        try (PreparedStatement ps2 = conn.prepareStatement(
                                "update usercart set quantity=? where useremail=? and prodid=?")) {

                            ps2.setInt(1, qty);
                            ps2.setString(2, userId);
                            ps2.setString(3, prodId);
                            ps2.executeUpdate();
                        }
                    } else {
                        try (PreparedStatement ps2 = conn.prepareStatement(
                                "delete from usercart where useremail=? and prodid=?")) {

                            ps2.setString(1, userId);
                            ps2.setString(2, prodId);
                            ps2.executeUpdate();
                        }
                    }

                    status = "Product Successfully removed from the Cart!";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return status;
    }

    @Override
    public Boolean removeAProduct(String userId, String prodId) {

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "delete from usercart where useremail=? and prodid=?")) {

            ps.setString(1, userId);
            ps.setString(2, prodId);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}