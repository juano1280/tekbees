/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.main;

import com.tekbees.connection.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class ConnTest {
    static Connection conn = DBConnection.getConnection();
    
    public static void main(String[] args) {
        
        
        try {
            Statement st = conn.createStatement();
            
            String sql = "select * from categoria";
            
            ResultSet r = st.executeQuery(sql);
            
            while(r.next()){
                System.out.println(r.getString("nom_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBConnection.closeConnection();
        
        conn = DBConnection.getConnection();
        
        try {
            Statement st = conn.createStatement();
            
            String sql = "select * from categoria";
            
            ResultSet r = st.executeQuery(sql);
            
            while(r.next()){
                System.out.println(r.getString("nom_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
}
