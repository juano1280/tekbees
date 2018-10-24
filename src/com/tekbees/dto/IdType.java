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
public enum IdType {
    
    CC("Cédula"), CE("Cédula Extranjería"), PP("Pasaporte");
    
    private final String typeDesc;
    private IdType(String tySpeDesc){
        this.typeDesc = tySpeDesc;
    }
    
    public String getDescription(){
        return typeDesc;
    }
}
