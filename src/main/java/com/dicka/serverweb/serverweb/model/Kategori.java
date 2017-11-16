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
@Table(name = "kategori", 
        catalog = "dbrestfull")
public class Kategori implements Serializable{
    
    @Id
    @Column(name = "idkategori")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idkategori;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "kodekategori", nullable = false)
    private String kodekategori;
    
    public int getIdkategori(){
        return idkategori;
    }
    
    public void setIdkategori(int idkategori){
        this.idkategori = idkategori;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public void setKodekategori(String kodekategori){
        this.kodekategori=kodekategori;
    }
    
    public String getKodekategori(){
        return kodekategori;
    }
}
