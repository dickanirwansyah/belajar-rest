/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.model.Kategori;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface KategoriDAO {
    
    Kategori insertKategori(Kategori kategori);
    
    Kategori updateKategori(Kategori kategori);
    
    void deleteKategori(Kategori kategori);
    
    boolean ifKategoriIsExist(Kategori kategori);
    
    List<Kategori> findAllKategori();
    
    Kategori findOneKategori(int idkategori);
}
