/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tekbees.facade;

import com.tekbees.dao.CategoryDAO;
import com.tekbees.dao.CityDAO;
import com.tekbees.dao.ProductDAO;
import com.tekbees.dto.CategoryDTO;
import com.tekbees.dto.CityDTO;
import com.tekbees.dto.ProductDTO;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Distribuitor {
    
    //Instance variable used to do CRUD operations in Caterogia Table
    private final CategoryDAO categoriaDAO;
    
    //Instance variable used to do CRUD operations in Product Table
    private final ProductDAO productDAO;
    
    //Instance variable that represents a category object
    private CategoryDTO categoriaDTO ;

    //Instance variable used to do CRUD operations in ciudad Table
    private final CityDAO cityDAO;

    public Distribuitor() {
        super();
        this.categoriaDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
        this.cityDAO = new CityDAO();
    }//End of constructor
    
    public void createNewCategory(String name, String description){
        categoriaDTO = new CategoryDTO(name, description);
        
        categoriaDAO.save(categoriaDTO);
    }//End of createNewCategory
    
    public void showAllCategories(){
        
        List<CategoryDTO> categories = new CategoryDAO().getAll();
        
        System.out.println("\n------------List of categories------------");
        
        categories.stream().forEach(System.out::print);
    }//End of showAllCategories
            
    public void updateCategoryName(int id, String name){
        
        System.out.println("Original data: ");
        categoriaDTO = categoriaDAO.getCategoryById(id);
        System.out.println(categoriaDTO);
        
        System.out.println("New data:");
        categoriaDTO.setNomCategoria(name);
        categoriaDAO.update(categoriaDTO);
        System.out.println(categoriaDTO);
        
    }//End of updateCategoryName
    
    public void updateCategoryDescription(int id, String description){
        
        System.out.println("Original data: ");
        categoriaDTO = categoriaDAO.getCategoryById(id);
        System.out.println(categoriaDTO);
        
        System.out.println("New data:");
        categoriaDTO.setDescCategoria(description);
        categoriaDAO.update(categoriaDTO);
        System.out.println(categoriaDTO);
        
    }//End of updateCategoryDescription
    
    public void showAllProducts(){
        List<ProductDTO> products = new ProductDAO().getAll();        
        System.out.println("\n------------List of products------------");
        
        products.stream().forEach(System.out::print);
    }
    
    public void createNewProduct(String barCode, String prodName, String prodDescription, double prodPrice, int stockQuantity, int categoryID){
        ProductDTO productDTO = new ProductDTO(barCode, prodName, prodDescription, prodPrice, stockQuantity, categoryID);
        
        productDAO.save(productDTO);
    }//End of createNewCategory
    
    public void updateProductName(String barCode, String prodName){
        
        ProductDTO producto = productDAO.getProductByID(barCode);
        
        producto.setProdName(prodName);
        
        productDAO.update(producto);
    }
    
    public void updateProductDescrption(String barCode, String prodDescrption){
        
        ProductDTO producto = productDAO.getProductByID(barCode);
        
        producto.setProdDescription(prodDescrption);
        
        productDAO.update(producto);        
    }
    
    public void updateProductQuantity(String barCode, int quantity){
        
        ProductDTO producto = productDAO.getProductByID(barCode);
        
        producto.setStockQuantity(quantity);
        
        productDAO.update(producto);       
    }
    
    public void showAllCities(){
        List<CityDTO> cities = cityDAO.getAll();
        
        System.out.println("\n------------List of cities------------");
        cities.stream().forEach(System.out::println);
    }
    
    public void createNewCity(String name){
        cityDAO.save(name);
    }
    
    public void populateDB(){
        /*
        distribuitor.createNewCategory("Beverage", "Beverages for human consume");
        distribuitor.createNewCategory("Cleaning", "Cleaning home products");
        distribuitor.createNewCategory("Furniture", "Home Furniture");
        distribuitor.createNewCategory("Healthy Food", "Organig Food");*/
        
        /*
        createNewProduct("7701023123412", "Coca-Cola", "Soda", 1.5, 30, 1);
        createNewProduct("7701023123413", "Heineken", "Imported Beer", 2.0, 20, 1);
        createNewProduct("7701023123414", "White Mount", "MIneral Water", 1.0, 40, 1);
        */
        
        createNewCity("Bogotá");
        createNewCity("Medellín");
        createNewCity("Cali");
        createNewCity("Barranquilla");
        createNewCity("Cartagena");
    }
}
