/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.dao;

import com.tekbees.connection.DBConnection;
import com.tekbees.dto.CityDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class CityDAO implements DAO{

    private CityDTO city;
    private Connection conn;
    private List<CityDTO> cities;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet result;
    private String sql;
    
    @Override
    public void startConnection() {
        
        conn = DBConnection.getConnection();//Stablish connection to DB
    }

    @Override
    public List<CityDTO> getAll() {
        
        startConnection();//Start conecction to DB
        
        try {
            
            cities = new ArrayList<>();
            
            stmt = conn.createStatement();
            
            sql = "SELECT * FROM CIUDAD";
            
            result = stmt.executeQuery(sql);
            
            while(result.next()){
                int id = result.getInt("id_ciudad");
                String name = result.getString("nom_ciudad");
                
                city = new CityDTO(id, name);
                
                cities.add(city);
            }
            
            result = null;
            return cities;
            
        } catch (SQLException ex) {
            System.out.println("Error in CityDAO.getAll: " +
                                ex.getMessage());
        }
        finally{
            closeResources();
        }
        
        return null;
    }//End of getAll

    @Override
    public void save(Object city) {
        
        String cityName = "";
        
        if(city instanceof String){
            cityName = (String)city; 
        }
        
        startConnection(); //Start connection to the DB
        
        sql = "INSERT INTO ciudad (nom_ciudad) VALUES('" + cityName + "')";
        
        try {
            stmt = conn.createStatement();
            
            if(stmt.executeUpdate(sql) > 0){
                System.out.println("The new city has been succesfully created");
            }
        } catch (SQLException ex) {
            System.out.println("Error in CityDAO.save: " + 
                               ex.getMessage());
        }
    }//End of save

    @Override
    public void update(Object city) {
        
        if(city instanceof CityDTO){
            this.city = (CityDTO)city;
        }
        
        startConnection();
        
        sql = "UPDATE ciudad SET nom_ciudad = " + this.city.getCityName() +
              " WHERE id_ciudad = " + this.city.getCityId();
        
        try {
            stmt = conn.createStatement();
            
            if(stmt.executeUpdate(sql) > 0){
                System.out.println("City has been succesfully updated");
            }
        } catch (SQLException ex) {
            System.out.println("Error in CityDAO.update: " +
                               ex.getMessage());
        }
    }//End of update

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//End of delete

    @Override
    public void closeResources() {
                try {
            if(result!=null)
                result.close();
            if(stmt!=null)
                stmt.close();
            if(pStmt!=null)
                pStmt.close();
        } catch (SQLException ex) {
            System.out.println("Error at City.closeResources: "+ex.getMessage());
        }
    }//End of closeResources
    
}
