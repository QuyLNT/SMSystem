/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class UserDAO {
    private static final String LOGIN = "SELECT " +
        "fullName, userName, password, phoneNumber, sex, email " +
        "FROM users WHERE userName=? AND password=?";


    
    public UserDTO checkLogin(String userName, String password) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userName);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if(rs.next()){
//                    int userId = rs.getInt("userId");
//                    String fullName = rs.getString("fullName");
//                    String phoneNumber = rs.getString("phoneNumber");
//                    String sex = rs.getString("sex");
//                    String email = rs.getString("email");
//                    String street = rs.getString("street");
//                    String district = rs.getString("district");
//                    String city = rs.getString("city");
//                    boolean isActive = rs.getBoolean("isActive");
//                    String roleId = rs.getString("roleId");
//                    Date createdAt = rs.getDate("createdAt");


                    user = new UserDTO();
                }
            }
                    

        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) rs.close();
        }
        
        return user;
    }
}
