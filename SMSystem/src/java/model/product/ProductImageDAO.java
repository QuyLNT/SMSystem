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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductImageDAO {
    private static final String GET_PRODUCT_IMAGE =    "SELECT imageId,imagePath,isAvatar\n" +
                                                "FROM productImages\n" +
                                                "WHERE productId = ?";
    
    public List<ProductImageDTO> getAllImage(int productId) throws ClassNotFoundException, SQLException{
        List<ProductImageDTO> listImage = new ArrayList();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_PRODUCT_IMAGE);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while(rs.next()){
                    int imageId = rs.getInt("imageId");
                    String path = rs.getString("imagePath");
                    boolean isAvatar = rs.getBoolean("isAvatar");
                    ProductImageDTO image = new ProductImageDTO(imageId, productId, path, isAvatar);
                    listImage.add(image);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) rs.close();
        }
        
        return listImage;
    }
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

