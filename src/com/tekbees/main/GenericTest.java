/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.main;

import com.tekbees.dao.ECityDAO;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class GenericTest {
    public static void main(String[] args) {
        System.out.println(ECityDAO.ship(new ECityDAO()));
        
        System.out.println(ECityDAO.<ECityDAO>ship(new ECityDAO()));
    }
}
