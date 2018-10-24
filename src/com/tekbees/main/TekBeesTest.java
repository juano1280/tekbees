/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.main;


import com.tekbees.facade.Distribuitor;


/**
 *
 * @author Juan
 */
public class TekBeesTest {
    
    private static Distribuitor distribuitor;
    
    public static void main(String[] args) {
        
        distribuitor = new Distribuitor();
        
        //distribuitor.populateDB();
        
        distribuitor.showAllCities();
        
        distribuitor.showAllCategories();
        
        distribuitor.showAllProducts();
        
        
    }//End of main
    
   
}
