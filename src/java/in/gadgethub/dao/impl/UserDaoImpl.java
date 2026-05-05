package in.gadgethub.dao.impl;

import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.UserPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.MailMessage;

import java.sql.*;
import javax.mail.MessagingException;

public class UserDaoImpl implements UserDao {

    @Override
    public String registerUser(UserPojo user) {

        if (isRegistered(user.getUserEmail())) {
            return "Email Already Registered. Try Again";
        }

        String sql = "insert into users values(?,?,?,?,?,?)";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getPincodeNumber());
            ps.setString(6, user.getPassword());

            if (ps.executeUpdate() == 1) {

                try {
                    MailMessage.registrationSuccess(
                            user.getUserEmail(),
                            user.getUserName()
                    );
                    System.out.println("Mail Sent Successfully");
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }

                return "Registration Successful";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "Registration Failed";
    }

    @Override
    public boolean isRegistered(String emailId) {

        String sql = "select 1 from users where useremail = ?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public String isValidCredentials(String emailId, String password) {

        String sql = "select 1 from users where useremail = ? and password=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailId);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    try {
                        UserPojo user = getUserDetails(emailId);
                        MailMessage.loginSuccess(emailId, user.getUserName());
                        System.out.println("Mail Sent Successfully");
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }

                    return "Login Successful";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }

        return "Login Denied! Incorrect Username/password";
    }

    @Override
    public UserPojo getUserDetails(String emailId) {

        String sql = "select * from users where useremail = ?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserPojo user = new UserPojo();
                    user.setUserEmail(rs.getString("useremail"));
                    user.setUserName(rs.getString("username"));
                    user.setMobile(rs.getString("mobile"));
                    user.setAddress(rs.getString("address"));
                    user.setPincodeNumber(rs.getInt("pincode"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public String getUserFirstName(String emailId) {

        String sql = "select username from users where useremail = ?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    return username.split(" ")[0];
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public String getUserAddr(String emailId) {

        String sql = "select address from users where useremail = ?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("address");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}