/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "produk", 
        catalog = "dbrestfull")
public class Produk implements Serializable{
    
    @Id @Column(name = "idproduk", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproduk;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;
    
    @Column(name = "harga", nullable = false)
    private int harga;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_kadaluarsa", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalKadaluarsa;
    
    @ManyToOne
    @JoinColumn(name = "idgudang", nullable = false)
    private Gudang gudang;
    
    public Produk(){
        
    }
    
    public Produk(String nama, String deskripsi, int harga, Date tanggalKadaluarsa, Gudang gudang){
        super();
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
        this.gudang = gudang;
    }
   
    public Gudang getGudang(){
        return gudang;
    }
    
    public void setGudang(Gudang gudang){
        this.gudang = gudang;
    }
    
    public int getIdproduk(){
        return idproduk;
    }
    
    public void setIdgudang(int idgudang){
        this.idproduk = idgudang;
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
    
    public int getHarga(){
        return harga;
    }
    
    public void setHarga(int harga){
        this.harga = harga;
    }
    
    public Date getTanggalKadaluarsa(){
        return tanggalKadaluarsa;
    }
    
    public void setTanggalKadaluarsa(Date tanggalKadaluarsa){
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }
}
