/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Juan
 */
public class SQLConnection {
    
    private static Connection conn = null;
    private static final String DRIVER;
    private static final String URL;
    private static final HashMap MAP = new HashMap();
    
    static{
        getParams();
        DRIVER = (String)MAP.get("driver");
        URL = (String)MAP.get("url");           
    }
    private SQLConnection(){
    }//End of constructor
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL);
            System.out.println("Succesfully connected to SQL Server");
            return conn;
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database connection error: " +
                                ex.getMessage());
        }

        return null;
    }
    
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
                System.out.println("Conexión finalizada");
            } catch (SQLException ex) {
                System.out.println("No es posible cerrar la conexión: " + ex.getMessage());
            }
        }
        else{
            System.out.println("Connection = null");
        }
    }
    private static void getParams(){
        Path path = Paths.get("res/SqlServer.res");
        try {
            Stream<String> stream = Files.lines(path);
            
            stream.forEach(s -> {
                                    int i = s.indexOf(" ");
                                    MAP.put(s.substring(0, i), s.substring(i+1));
                                    //System.out.println("Key:"+s.substring(0, i)+". Value:"+s.substring(i+1));
                                });
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
