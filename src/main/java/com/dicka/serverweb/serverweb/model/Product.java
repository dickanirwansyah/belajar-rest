/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "product", 
        catalog = "dbrestfull")
public class Product implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproduct;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "vendor", nullable = false)
    private String vendor;
    
    @Column(name = "price", nullable = false)
    private int price;
    
    public int getIdproduct(){
        return idproduct;
    }
    
    public void setIdproduct(int idproduct){
        this.idproduct = idproduct;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getVendor(){
        return vendor;
    }
    
    public void setVendor(String vendor){
        this.vendor = vendor;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
}
