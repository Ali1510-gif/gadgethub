/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LAPTOP
 */
public class DBUtil {
     private static Connection conn;
     private static String url;
     private static String username;
     private static String password;
    public static void openConnection(String dbUrl, String dbUsername, String dbPassword){
        url = dbUrl;
        username = dbUsername;
        password = dbPassword;
    }

    public static Connection provideConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            return conn;
        }catch(Exception ex){
            System.out.println("Connection not opened : "+ex.getMessage());
            throw new RuntimeException("Connection not opened ",ex);
        }
    }
    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error in closing connection");
                ex.printStackTrace();
            }
        }
    }
    
    public static void closeStatement(Statement st)
    {
        if(st!=null)
        {
            try
            {
                st.close();
            }
            catch(SQLException ex){
                System.out.println("Error in closing Connection");
                ex.printStackTrace();
            }
        }
    }
    
}
