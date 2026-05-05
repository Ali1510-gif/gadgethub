package in.gadgethub.dao.impl;

import in.gadgethub.dao.*;
import in.gadgethub.pojo.*;
import in.gadgethub.utility.*;

import java.sql.*;
import java.util.*;
import javax.mail.MessagingException;

public class ProductDaoImpl implements ProductDao {

    @Override
    public String addProduct(ProductPojo product) {

        if (product.getProdId() == null) {
            product.setProdId(IDUtil.generateProdId());
        }

        String sql = "insert into products values(?,?,?,?,?,?,?,?)";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getProdId());
            ps.setString(2, product.getProdName());
            ps.setString(3, product.getProdType());
            ps.setString(4, product.getProdInfo());
            ps.setDouble(5, product.getProdPrice());
            ps.setInt(6, product.getProdQuantity());
            ps.setBlob(7, product.getProdImage());
            ps.setString(8, "Y");

            return ps.executeUpdate() == 1
                    ? "Product Added Successfully with Id: " + product.getProdId()
                    : "Product Registration Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Product Registration Failed";
        }
    }

    @Override
    public String updateProduct(ProductPojo pre, ProductPojo updated) {

        if (!pre.getProdId().equals(updated.getProdId())) {
            return "Product ID's Do Not Match. Updation Failed";
        }

        String sql = "update products set pname=?,ptype=?,pinfo=?,pprice=?,pquantity=?,image=? where pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updated.getProdName());
            ps.setString(2, updated.getProdType());
            ps.setString(3, updated.getProdInfo());
            ps.setDouble(4, updated.getProdPrice());
            ps.setInt(5, updated.getProdQuantity());
            ps.setBlob(6, updated.getProdImage());
            ps.setString(7, updated.getProdId());

            return ps.executeUpdate() == 1
                    ? "Product Updated Successfully"
                    : "Updation Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Updation Failed";
        }
    }

    @Override
    public String updateProductPrice(String prodId, double price) {

        String sql = "update products set pprice=? where pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, price);
            ps.setString(2, prodId);

            return ps.executeUpdate() == 1
                    ? "Product Price Updated Successfully"
                    : "Price Updation Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<ProductPojo> getAllProducts() {

        List<ProductPojo> list = new ArrayList<>();
        String sql = "select * from products where available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ProductPojo p = new ProductPojo();
                p.setProdId(rs.getString("pid"));
                p.setProdName(rs.getString("pname"));
                p.setProdType(rs.getString("ptype"));
                p.setProdInfo(rs.getString("pinfo"));
                p.setProdPrice(rs.getDouble("pprice"));
                p.setProdQuantity(rs.getInt("pquantity"));
                p.setProdImage(rs.getAsciiStream("image"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ProductPojo> getAllProductByType(String type) {

        List<ProductPojo> list = new ArrayList<>();
        String sql = "select * from products where lower(ptype) like ? and available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + type.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductPojo p = new ProductPojo();
                    p.setProdId(rs.getString("pid"));
                    p.setProdName(rs.getString("pname"));
                    p.setProdType(rs.getString("ptype"));
                    p.setProdInfo(rs.getString("pinfo"));
                    p.setProdPrice(rs.getDouble("pprice"));
                    p.setProdQuantity(rs.getInt("pquantity"));
                    p.setProdImage(rs.getAsciiStream("image"));
                    list.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ProductPojo> searchAllProducts(String term) {

        List<ProductPojo> list = new ArrayList<>();
        String sql = "select * from products where (lower(ptype) like ? or lower(pname) like ? or lower(pinfo) like ?) and available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            term = "%" + term.toLowerCase() + "%";
            ps.setString(1, term);
            ps.setString(2, term);
            ps.setString(3, term);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductPojo p = new ProductPojo();
                    p.setProdId(rs.getString("pid"));
                    p.setProdName(rs.getString("pname"));
                    p.setProdType(rs.getString("ptype"));
                    p.setProdInfo(rs.getString("pinfo"));
                    p.setProdPrice(rs.getDouble("pprice"));
                    p.setProdQuantity(rs.getInt("pquantity"));
                    p.setProdImage(rs.getAsciiStream("image"));
                    list.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ProductPojo getProductDetails(String prodId) {

        String sql = "select * from products where pid=? and available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductPojo p = new ProductPojo();
                    p.setProdId(rs.getString("pid"));
                    p.setProdName(rs.getString("pname"));
                    p.setProdType(rs.getString("ptype"));
                    p.setProdInfo(rs.getString("pinfo"));
                    p.setProdPrice(rs.getDouble("pprice"));
                    p.setProdQuantity(rs.getInt("pquantity"));
                    p.setProdImage(rs.getAsciiStream("image"));
                    return p;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getProductQuantity(String prodId) {

        String sql = "select pquantity from products where pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getProductPrice(String prodId) {

        String sql = "select pprice from products where pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Boolean sellNProduct(String prodId, int n) {

        String sql = "update products set pquantity=(pquantity-?) where pid=? and available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, n);
            ps.setString(2, prodId);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<String> getAllProductsType() {

        List<String> list = new ArrayList<>();
        String sql = "select distinct ptype from products where available='Y'";

        try (Connection conn = DBUtil.provideConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public byte[] getImage(String prodId) {

        String sql = "select image from products where pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getBytes(1) : null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String removeProduct(String prodId) {

        String updateSQL = "update products set available='N' where pid=? and available='Y'";
        String deleteSQL = "delete from usercart where prodid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps1 = conn.prepareStatement(updateSQL);
                PreparedStatement ps2 = conn.prepareStatement(deleteSQL)) {

            ps1.setString(1, prodId);

            if (ps1.executeUpdate() > 0) {
                ps2.setString(1, prodId);
                ps2.executeUpdate();
                return "Product Removed Successfully";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }

        return "Product Not Found!";
    }

    @Override
    public String updateProductWithoutImage(String prevProdId, ProductPojo updatedProduct) {

        String status = "Updation Failed!";

        if (!prevProdId.equals(updatedProduct.getProdId())) {
            return "Product ID's Do Not Match. Updation Failed";
        }

        String sql = "UPDATE products SET pname=?, ptype=?, pinfo=?, pprice=?, pquantity=? WHERE pid=?";

        try (Connection conn = DBUtil.provideConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            int prevQuantity = getProductQuantity(prevProdId);

            ps.setString(1, updatedProduct.getProdName());
            ps.setString(2, updatedProduct.getProdType());
            ps.setString(3, updatedProduct.getProdInfo());
            ps.setDouble(4, updatedProduct.getProdPrice());
            ps.setInt(5, updatedProduct.getProdQuantity());
            ps.setString(6, updatedProduct.getProdId());

            int count = ps.executeUpdate();

            if (count == 1 && prevQuantity < updatedProduct.getProdQuantity()) {

                status = "Product Updated Successfully And Mail Sent";

                // 🔥 Demand handling + mail
                DemandDao demandDao = new DemandDaoImpl();
                List<DemandPojo> demands = demandDao.haveDemanded(prevProdId);

                for (DemandPojo demand : demands) {

                    String userEmail = demand.getUserEmail();
                    String prodId = demand.getProdId();
                    int qty = demand.getdemandQuantity();

                    UserDao userDao = new UserDaoImpl();
                    String userName = userDao.getUserFirstName(userEmail);

                    if (qty <= updatedProduct.getProdQuantity()) {

                        try {
                            MailMessage.sendDemandFulfilledEmail(
                                    userEmail,
                                    userName,
                                    prodId,
                                    updatedProduct.getProdName(),
                                    qty,
                                    updatedProduct.getProdQuantity()
                            );

                            System.out.println("Mail sent successfully");

                            // remove demand entry
                            boolean removed = demandDao.removeProduct(userEmail, prodId);
                            System.out.println("Demand removed: " + removed);

                        } catch (MessagingException ex) {
                            System.out.println("Error while sending mail");
                            ex.printStackTrace();
                        }
                    }
                }

            } else if (count == 1) {
                status = "Product Updated Successfully";
            }

        } catch (SQLException ex) {
            System.out.println("Error in updateProductWithoutImage: " + ex);
            ex.printStackTrace();
        }

        return status;
    }
}
