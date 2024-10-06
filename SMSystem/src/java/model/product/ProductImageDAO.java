/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductImageDAO {
    private static final String INSERT_IMAGE_SQL = "INSERT INTO Product_Images (productId, imagePath) VALUES (?, ?)";
    
    public void addProductImage(int productId, String image) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(INSERT_IMAGE_SQL);
            ps.setInt(1, productId);
            ps.setString(2, image);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}

