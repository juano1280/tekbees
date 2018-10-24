/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.dao;

import com.tekbees.connection.DBConnection;
import com.tekbees.connection.SQLConnection;
import com.tekbees.dto.CategoryDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class CategoryDAO implements DAO{

    List<CategoryDTO> categoriesList;
    Connection conn; // Object
    PreparedStatement pStmt;
    Statement stmt;
    ResultSet result;
    CategoryDTO categoriaDTO;

    @Override
    public void startConnection(){
        if(conn == null){
            conn = DBConnection.getConnection();
            //conn = SQLConnection.getConnection();
        }
    }
    
    @Override
    /**
     * Retrieves a full list of categories from the Data Base
     */
    public List<CategoryDTO> getAll() {
        
        categoriesList = new ArrayList<>();//ArrayList with all categories
        
        String sql = "SELECT * FROM categoria";
        
        startConnection();
        
        try {
            stmt = conn.createStatement();//Retrieve Statement from Connection
            
            result = stmt.executeQuery(sql);//Retrieve data from Category Table
            
            while(result.next()){
                //Iterate over result and save data
                int idCategory = result.getInt("id_categoria");
                String nomCategory = result.getString("nom_categoria");
                String descCategory = result.getString("desc_categoria");
                
                //Create a new categoriaDTO instance
                categoriaDTO = new CategoryDTO(idCategory, nomCategory, descCategory);
                
                categoriesList.add(categoriaDTO);//Put instance in the list
            }//End of while
            
            return categoriesList;
            
        } catch (SQLException ex) {
            
            System.out.println("Error in CategoriaDAO.getAll method: "+ex.getMessage());
        }
        finally{
            closeResources();
        }
        
        return null;
    }//End of getAll

    @Override
    /**
     * Puts a new category into the Data Base
     * @param(CategoriaDTO c)
     */
    public void save(Object category) {
        categoriaDTO = (CategoryDTO)category;
        
        String sql = "INSERT INTO categoria(nom_categoria,desc_categoria) VALUES(?,?)";
        
        startConnection();
        
        try {
            pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, categoriaDTO.getNomCategoria());
            pStmt.setString(2, categoriaDTO.getDescCategoria());
            
            if(pStmt.executeUpdate()>0){
                System.out.println("New category succesfully created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error in CategoryDAO.save: " + ex.getMessage());
        }
        finally{
            closeResources();
        }
    }//End of save

    @Override
    public void update(Object category) {
        
        categoriaDTO = (CategoryDTO)category;
        
        String sql = "UPDATE categoria SET nom_categoria = ?, desc_categoria = ? " +
                    "WHERE id_categoria = ?";
        
        startConnection();
        
        try {
            pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, categoriaDTO.getNomCategoria());
            pStmt.setString(2, categoriaDTO.getDescCategoria());
            pStmt.setInt(3, categoriaDTO.getIdCategoria());
            
            if(pStmt.executeUpdate()>0){
                System.out.println("Category succesfully updated.");
            }
        } catch (SQLException ex) {
            System.out.println("Error in CategoryDAO.update: " + ex.getMessage());
        }
        finally{
            closeResources();
        }
        
    }//End of updtae

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public CategoryDTO getCategoryById(int id){
        
        String sql = "SELECT * FROM categoria WHERE id_categoria = " + id;
        
        startConnection();
        
        try {
            stmt = conn.createStatement();//Retrieve Statement from Connection
            
            result = stmt.executeQuery(sql);//Retrieve data from Category Table
            
            while(result.next()){
                //Iterate over result and save data
                int idCategory = result.getInt("id_categoria");
                String nomCategory = result.getString("nom_categoria");
                String descCategory = result.getString("desc_categoria");
                
                //Create a new categoriaDTO instance
                categoriaDTO = new CategoryDTO(idCategory, nomCategory, descCategory);
            }//End of while
            
            return categoriaDTO;
            
        } catch (SQLException ex) {
            
            System.out.println("Error in CategoriaDAO.getAll method: "+ex.getMessage());
        }
        finally{
            closeResources();
        }        
        return null;
    }//End of getCategoryById
    
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