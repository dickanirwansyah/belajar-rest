/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.controller;

import com.dicka.serverweb.serverweb.dao.KategoriDAO;
import com.dicka.serverweb.serverweb.model.Kategori;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@RestController
@RequestMapping(value = "/api")
@Service
@CrossOrigin(value = {"http://localhost:9000"})
public class ControllerKategori {
    
    //logger debug
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerKategori.class);
    
    private KategoriDAO kategoriDAO;
    
    @Inject
    public ControllerKategori(KategoriDAO kategoriDAO){
        this.kategoriDAO = kategoriDAO;
    }
    
    @GetMapping(value = "/kategoris")
    public ResponseEntity<List<Kategori>> getListKategori(){
        List<Kategori> listkategori = kategoriDAO.findAllKategori();
        if(listkategori.isEmpty()){
            LOGGER.debug("DEBUG : list kategori");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listkategori, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertKategori")
    public ResponseEntity<Kategori>insertKategori(@RequestBody Kategori kategori, UriComponentsBuilder builder){
        LOGGER.debug("DEBUG : insert");
        if(kategoriDAO.ifKategoriIsExist(kategori)){
            LOGGER.debug("Data sudah ada");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        kategoriDAO.insertKategori(kategori);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertKategori/{idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<>(kategori, headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/updateKategori/{idkategori}")
    public ResponseEntity<Kategori>updateKategori(@PathVariable String idkategori, @RequestBody Kategori kategori, 
            UriComponentsBuilder builder){
        
        LOGGER.debug("DEBUG : update");
        Kategori kk = kategoriDAO.findOneKategori(Integer.parseInt(idkategori));
        if(kk == null){
            LOGGER.debug("DEBUG : data tidak ada");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        kk.setKodekategori(kategori.getKodekategori());
        kk.setNama(kategori.getNama());
        kategoriDAO.updateKategori(kk);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updateKategori/{idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<>(kk, headers, HttpStatus.ACCEPTED);
    }
    
    @GetMapping(value = "/kategoris/{idkategori}")
    public ResponseEntity<Kategori>findOneKategori(@PathVariable String idkategori, UriComponentsBuilder builder){
        LOGGER.debug("DEBUG : findone");
        Kategori kategori = kategoriDAO.findOneKategori(Integer.parseInt(idkategori));
        if(kategori == null){
            LOGGER.debug("DEBUG : kategori is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/kategoris/{idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<>(kategori, headers, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deleteKategori/{idkategori}")
    public ResponseEntity<Void>deleteKategori(@PathVariable String idkategori){
        LOGGER.debug("DEBUG : delete kategori");
        Kategori kategori = kategoriDAO.findOneKategori(Integer.parseInt(idkategori));
        if(kategori == null){
            LOGGER.debug("DEBUG : kategori is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        kategoriDAO.deleteKategori(kategori);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
