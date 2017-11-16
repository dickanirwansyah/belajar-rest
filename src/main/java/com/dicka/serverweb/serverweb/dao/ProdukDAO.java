/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.model.Produk;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface ProdukDAO {
   
    List<Produk> listproduk();
    
    Produk findOneProduk(int idproduk);
    
    Produk insertProduk(Produk produk);
    
    Produk updateProduk(Produk produk);
    
    void deleteProduk(Produk produk);
    
    boolean ifProdukIsExist(Produk produk);
}
