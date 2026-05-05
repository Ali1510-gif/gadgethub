package in.gadgethub.dao.impl;

import in.gadgethub.dao.DemandDao;
import in.gadgethub.dao.ProductDao;
import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.pojo.ProductPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.MailMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

public class DemandDaoImpl implements DemandDao {

    @Override
    public boolean addProduct(DemandPojo demandPojo) {
        boolean status = false;

        String updateSQL = "Update userdemand set quantity=quantity+? where useremail=? and prodid=?";
        String insertSQL = "Insert into userdemand values(?,?,?)";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps1 = conn.prepareStatement(updateSQL)) {

            ps1.setInt(1, demandPojo.getdemandQuantity());
            ps1.setString(2, demandPojo.getUserEmail());
            ps1.setString(3, demandPojo.getProdId());

            int k = ps1.executeUpdate();

            if (k == 0) {
                try (PreparedStatement ps2 = conn.prepareStatement(insertSQL)) {
                    ps2.setString(1, demandPojo.getUserEmail());
                    ps2.setString(2, demandPojo.getProdId());
                    ps2.setInt(3, demandPojo.getdemandQuantity());
                    ps2.executeUpdate();
                }
            }

            // Mail logic
            String userEmail = demandPojo.getUserEmail();

            UserDao userDao = new UserDaoImpl();
            String userName = userDao.getUserFirstName(userEmail);

            ProductDao productDao = new ProductDaoImpl();
            ProductPojo products = productDao.getProductDetails(demandPojo.getProdId());

            MailMessage.demandSuccess(
                    userEmail,
                    userName,
                    products.getProdName(),
                    demandPojo.getdemandQuantity(),
                    productDao.getProductQuantity(demandPojo.getProdId())
            );

            System.out.println("Mail Sent Successfully");
            status = true;

        } catch (SQLException ex) {
            System.out.println("Error in addProduct:" + ex);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Exception occurred while sending mail...");
            ex.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeProduct(String userId, String prodId) {
        String sql = "delete from userdemand where useremail=? and prodid=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            ps.setString(2, prodId);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Exception in removeProduct: " + ex);
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<DemandPojo> haveDemanded(String prodId) {
        List<DemandPojo> demandList = new ArrayList<>();

        String sql = "select * from userdemand where prodId=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DemandPojo demandPojo = new DemandPojo();
                    demandPojo.setUserEmail(rs.getString("useremail"));
                    demandPojo.setProdId(rs.getString("prodid"));
                    demandPojo.setQuantity(rs.getInt("quantity"));
                    demandList.add(demandPojo);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Exception in haveDemanded: " + ex);
            ex.printStackTrace();
        }

        return demandList;
    }
}