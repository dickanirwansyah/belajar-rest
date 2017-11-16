/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.repository;

import com.dicka.serverweb.serverweb.model.Kategori;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author java-spring
 */
@RepositoryRestResource
public interface RepositoryKategori extends JpaRepository<Kategori, Integer>{
    
}
