package com.fernandez.entity;

import java.math.BigDecimal;

public class Product {

    private Integer productId;
    private String prodName;
    private BigDecimal price;
    private Integer unit;
    private String product;

    public Product(Integer productId, String prodName, BigDecimal price, Integer unit, String product) {
        this.productId = productId;
        this.prodName = prodName;
        this.price = price;
        this.unit = unit;
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                ", product='" + product + '\'' +
                '}';
    }
}
