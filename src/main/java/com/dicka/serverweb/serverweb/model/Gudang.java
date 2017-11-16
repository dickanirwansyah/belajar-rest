/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "gudang", 
        catalog = "dbrestfull")
public class Gudang implements Serializable{
    
    @Id @Column(name = "idgudang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgudang;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;
    
    @JsonIgnore
    @OneToMany(mappedBy = "gudang")
    @Column(nullable = true)
    private Set<Produk> produks = new HashSet<Produk>();
    
    public Gudang(){
        
    }
    
    public Gudang(String nama, String deskripsi){
        super();
        this.nama = nama;
        this.deskripsi = deskripsi;
    }
    
    public Set<Produk>getProduks(){
        return produks;
    }
    
    public void setProduks(Set<Produk> produks){
        this.produks = produks;
    }
    
    public int getIdgudang(){
        return idgudang;
    }
    
    public void setIdgudang(int idgudang){
        this.idgudang = idgudang;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getDeskripsi(){
        return deskripsi;
    }
    
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    
    
}
