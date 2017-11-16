/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.model.Gudang;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface GudangDAO {
    
    Gudang insertGudang(Gudang gudang);
    
    Gudang updateGudang(Gudang gudang);
    
    void deleteGudang(Gudang gudang);
    
    Gudang findOneGudangById(int idgudang);
    
    List<Gudang> findAllGudang();
    
    boolean ifGudangIsExist(Gudang gudang);
}
