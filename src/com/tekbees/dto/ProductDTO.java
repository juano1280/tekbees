/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.dto;

/**
 *
 * @author Juan
 */
public class ProductDTO {
    private String barCode;
    private String prodName;
    private String prodDescription;
    private double prodPrice;
    private int stockQuantity;
    private int categoryID;
    private CategoryDTO prodCategory;

    public ProductDTO(String barCode, String prodName, String prodDescription, double prodPrice, int stockQuantity, int categoryID, CategoryDTO prodCategory) {
        this.barCode = barCode;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodPrice = prodPrice;
        this.stockQuantity = stockQuantity;
        this.categoryID = categoryID;
        this.prodCategory = prodCategory;
    }

    public ProductDTO(String barCode, String prodName, String prodDescription, double prodPrice, int stockQuantity, int categoryID) {
        this.barCode = barCode;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodPrice = prodPrice;
        this.stockQuantity = stockQuantity;
        this.categoryID = categoryID;
    }

    public ProductDTO(String barCode, String prodName, String prodDescription, double prodPrice, int stockQuantity, CategoryDTO prodCategory) {
        this.barCode = barCode;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodPrice = prodPrice;
        this.stockQuantity = stockQuantity;
        this.prodCategory = prodCategory;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        if(categoryID<0){
            categoryID=0;
        }
        this.categoryID = categoryID;
    }

    public CategoryDTO getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(CategoryDTO prodCategory) {
        this.prodCategory = prodCategory;
    }
    
    @Override
    public String toString(){
        return "Bar Code: " + this.barCode + "\n" +
               "Producto Name: " + this.prodName + "\n" +
               "Product Description: " + this.prodDescription + "\n" +
               "Product Price: " + this.prodPrice + "\n" +
               "Units in stock: " + this.stockQuantity + "\n" +
               "Category: " + getProdCategory().getNomCategoria() + "\n" +
               "-----------------------------------------\n";
    }
}
