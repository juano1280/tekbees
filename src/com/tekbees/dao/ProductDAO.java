/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.dao;

import com.tekbees.connection.DBConnection;
import com.tekbees.dto.CategoryDTO;
import com.tekbees.dto.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Juan
 */
public class ProductDAO implements DAO{

    private final List<ProductDTO> productList;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pStmt;
    private ResultSet result;
    private String sql;
    private ProductDTO product;

    public ProductDAO() {
        this.productList = new ArrayList<>();
    }
    
    @Override
    public void startConnection() {
        conn = DBConnection.getConnection();
    }//End of startConnection

    @Override
    public List<ProductDTO> getAll() {
        
        startConnection();
        
        sql = "SELECT * FROM producto";
        
        try {
            pStmt = conn.prepareStatement(sql);
            
            result = pStmt.executeQuery();
            
            while(result.next()){
                String barCode = result.getString("cod_barras_producto");
                String prodName = result.getString("nom_producto");
                String prodDescription = result.getString("desc_producto");
                double prodPrice = result.getDouble("precio_producto");
                int stockQuantity = result.getInt("cant_stock_producto");
                int categoryID = result.getInt("id_categoria");
                
                CategoryDTO category = new CategoryDAO().getCategoryById(categoryID);
                
                product = new ProductDTO(barCode, prodName, prodDescription, prodPrice, stockQuantity, category);
                
                productList.add(product);
            }//End of while
            
            return productList;
        } catch (SQLException ex) {
            System.out.println("Error in ProductDAO.getAll: " +
                                ex.getMessage());
        }
        finally{
            closeResources();
        }
        
        return null;
    }//End of getAll products

    @Override
    public void save(Object product) {
        
        if(product instanceof ProductDTO){
            this.product = (ProductDTO)product;
        }
        
        try {
            startConnection();
            
            sql = "INSERT INTO PRODUCTO VALUES (?,?,?,?,?,?)";
            
            
            pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, this.product.getBarCode());
            pStmt.setString(2, this.product.getProdName());
            pStmt.setString(3, this.product.getProdDescription());
            pStmt.setDouble(4, this.product.getProdPrice());
            pStmt.setInt(5, this.product.getStockQuantity());
            pStmt.setInt(6, this.product.getCategoryID());
            
            if(pStmt.executeUpdate()>0){
                System.out.println("Product succesfully saved.");
            }
        } //End of save product
        catch (SQLException ex) {
            System.out.println("Error in ProductDAO.save(): " +
                               ex.getMessage());
        }
        finally{
            closeResources();
        }
    }

    @Override
    public void update(Object product) {
        try {
            this.product = (ProductDTO)product;
            
            startConnection();
            
            sql = "UPDATE PRODUCTO SET nom_producto = ?, desc_producto = ?, cant_stock_producto = ?" +
                  " WHERE cod_barras_producto = ?";
            
            pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, this.product.getProdName());
            pStmt.setString(2, this.product.getProdDescription());
            pStmt.setInt(3, this.product.getStockQuantity());
            pStmt.setString(4, this.product.getBarCode());
            
            if(pStmt.executeUpdate()>0){
                System.out.println("Product updated sucessfully.");
            }
        } 
        catch (SQLException ex) {
            System.out.println("Error in ProductDAO.update: " + 
                               ex.getMessage());
        }
        finally{
            closeResources();
        }
    }//End of update

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ProductDTO getProductByID(String barCode){
        
        startConnection();
        
        try {
            stmt = conn.createStatement();
            
            sql = "SELECT * FROM producto WHERE cod_barras_producto = ?";
            
            pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, barCode);
            
            result = pStmt.executeQuery();
            
            while(result.next()){
                String prodName = result.getString("nom_producto");
                String prodDescription = result.getString("desc_producto");
                double prodPrice = result.getDouble("precio_producto");
                int stockQuantity = result.getInt("cant_stock_producto");
                int categoryID = result.getInt("id_categoria");
                
                return new ProductDTO(barCode, prodName, prodDescription, prodPrice, stockQuantity, categoryID);
            }
        } catch (SQLException ex) {
            System.out.println("Error in ProductDAO.getProductByID: " +
                               ex.getMessage());
        }
        finally{
            closeResources();
        }
        return null;
    }
    @Override
    public void closeResources(){
        try {
            if(result!=null)
                result.close();
            if(stmt!=null)
                stmt.close();
            if(pStmt!=null)
                pStmt.close();
        } catch (SQLException ex) {
            System.out.println("Error at CategoryDAO.closeResources: "+ex.getMessage());
        }
    }//End of closeResources
}
