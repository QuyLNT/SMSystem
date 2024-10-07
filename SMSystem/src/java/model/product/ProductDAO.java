/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.user.UserDTO;
import utils.DBUtils;

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
    private static final String UPDATE_PRODUCT =    "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n" +
                                                    "FROM products\n" +
                                                    "WHERE productId=?";
    
    
    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> listProduct = new ArrayList();
        return listProduct;
    }
    
    public ProductDTO getProductById(int productId) throws ClassNotFoundException, SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                if(rs.next()){
                    int brandId = rs.getInt("brandId");
                    String userObjectId = rs.getString("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    double price = rs.getDouble("price");
                    double sale = rs.getDouble("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    String productStatus = rs.getString("productStatus");

                    // Create a new ProductDTO object using the fetched data
                    product = new ProductDTO(productId, brandId, productId, detail, hot, name, color, brandId, brandId, warrantPeriod, hot);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) rs.close();
        }
        
        return product;
    }
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
