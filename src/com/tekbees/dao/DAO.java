/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.dao;

import java.util.List;

/**
 *
 * @author Juan
 */
public interface DAO<T> {
    
    void startConnection();
    
    List<T> getAll();//Method for retrieve all elements from the table
    
    void save(T t);//Method to save a new object
    
    void update(T t);//Method to update an object
    
    void delete(T t);//Method to delete an object
    
    void closeResources();
}
