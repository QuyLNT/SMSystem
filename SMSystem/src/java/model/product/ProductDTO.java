/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

/**
 *
 * @author LENOVO
 */
public class ProductDTO {
    private int productId;
    private int brandId;
    private int userOjectId;
    private String detail;
    private boolean hot;
    private String name;
    private String color;
    private float price;
    private float sale;
    private int warrantyPeriod;
    private boolean productStaus;

    public ProductDTO() {
        this.productId = 0;
        this.brandId = 0;
        this.userOjectId = 0;
        this.detail = "";
        this.hot = true;
        this.name = "";
        this.color = "";
        this.price = 0.00f;
        this.sale = 0.0f;
        this.warrantyPeriod = 0;
        this.productStaus = false;
    }

    public ProductDTO(int productId, int brandID, int userOjectId, String detail, boolean hot, String name, String color, float price, float sale, int warrantyPeriod, boolean productStaus) {
        this.productId = productId;
        this.brandId = brandID;
        this.userOjectId = userOjectId;
        this.detail = detail;
        this.hot = hot;
        this.name = name;
        this.color = color;
        this.price = price;
        this.sale = sale;
        this.warrantyPeriod = warrantyPeriod;
        this.productStaus = productStaus;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandID() {
        return brandId;
    }

    public int getUserOjectId() {
        return userOjectId;
    }

    public String getDetail() {
        return detail;
    }

    public boolean isHot() {
        return hot;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public float getPrice() {
        return price;
    }

    public float getSale() {
        return sale;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public boolean isProductStaus() {
        return productStaus;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setBrandID(int brandID) {
        this.brandId = brandID;
    }

    public void setUserOjectId(int userOjectId) {
        this.userOjectId = userOjectId;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public void setProductStaus(boolean productStaus) {
        this.productStaus = productStaus;
    }
    
    
    
    
 
}
