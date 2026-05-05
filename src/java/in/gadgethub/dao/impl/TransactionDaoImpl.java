package in.gadgethub.dao.impl;

import in.gadgethub.dao.TransactionDao;
import in.gadgethub.utility.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public String getUserId(String transId) {

        String userId = null; // better than ""

        String sql = "select useremail from transactions where transid=?";

        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getString(1);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Exception occurred in getUserId method: " + ex);
            ex.printStackTrace();
        }

        return userId;
    }
}