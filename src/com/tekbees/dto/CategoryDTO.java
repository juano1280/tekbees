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
public class CategoryDTO {
    //Instance fields
    private int idCategoria;
    private String nomCategoria;
    private String descCategoria;

    //Class constructor
    public CategoryDTO(int idCategoria, String nomCategoria, String descCategoria) {
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
        this.descCategoria = descCategoria;
    }//End of Class constructor
    
    //Class constructor with default value for idCategoria = 0
    public CategoryDTO(String nomCategoria, String descCategoria) {
        this(0,nomCategoria,descCategoria);
    }//End of Class constructor

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
    
    @Override
    public String toString(){
        return "ID: " + this.idCategoria + "\n" +
                "Name: " + this.nomCategoria + "\n" +
                "Description: " + this.descCategoria + "\n" +
                "-------------------------------------------\n";
    }
}
