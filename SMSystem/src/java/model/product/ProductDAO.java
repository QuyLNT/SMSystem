/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductDAO {
    public int addProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        int productId = -1;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        Connection con = null;
        String sql = "INSERT INTO Product (productId, brandId, userOjectId, detail, hot, name, color, price, sale, warrantyPeriod, productStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getProductId());
            ps.setInt(2, product.getBrandID());
            ps.setInt(3, product.getUserOjectId());
            ps.setString(4, product.getDetail());
            ps.setBoolean(5, product.isHot());
            ps.setString(6, product.getName());
            ps.setString(7, product.getColor());
            ps.setFloat(8, product.getPrice());
            ps.setFloat(9, product.getSale());
            ps.setInt(10, product.getWarrantyPeriod());
            ps.setBoolean(11, product.isProductStaus());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return productId;
    }
}
