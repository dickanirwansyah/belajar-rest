/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.controller;

import com.dicka.serverweb.serverweb.dao.ProdukDAO;
import com.dicka.serverweb.serverweb.model.Produk;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = {"http://localhost:9000"})
public class ControllerProduk {
    
    private ProdukDAO produkDAO;
    
    private static Logger LOGGER = LoggerFactory.getLogger(ControllerProduk.class);
    
    @Autowired
    public ControllerProduk(ProdukDAO produkDAO){
        this.produkDAO = produkDAO;
    }
    
    @GetMapping(value = "/produks")
    public ResponseEntity<List<Produk>>findAllProduk(){
        LOGGER.info("INFO : list produk");
        List<Produk> listproduks = produkDAO.listproduk();
        if(listproduks.isEmpty()){
            LOGGER.info("INFO : data kosong");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listproduks, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertProduk")
    public ResponseEntity<Produk>insertProduk(@RequestBody Produk produk, UriComponentsBuilder builder){
        LOGGER.info("INFO : insert produk");
        if(produkDAO.ifProdukIsExist(produk)){
            LOGGER.info("Data sudah ada");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        produkDAO.insertProduk(produk);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertProduk/{idproduk}")
                .buildAndExpand(produk.getIdproduk()).toUri());
        return new ResponseEntity<>(produk, headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/updateProduk/{idproduk}")
    public ResponseEntity<Produk>updateProduk(@PathVariable String idproduk,
            @RequestBody Produk produk,
            UriComponentsBuilder builder){
        
        LOGGER.info("INFO : update produk");
        Produk currentProduk = produkDAO.findOneProduk(Integer.parseInt(idproduk));
        if(currentProduk == null){
            LOGGER.info("INFO : update produk fail");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        currentProduk.setDeskripsi(produk.getDeskripsi());
        currentProduk.setHarga(produk.getHarga());
        currentProduk.setNama(produk.getNama());
        currentProduk.setTanggalKadaluarsa(produk.getTanggalKadaluarsa());
        currentProduk.setGudang(produk.getGudang());
        produkDAO.updateProduk(currentProduk);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updateProduk/{idproduk}")
                .buildAndExpand(produk.getIdproduk()).toUri());
        return new ResponseEntity<>(currentProduk, headers, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping(value = "/deleteProduk/{idproduk}")
    public ResponseEntity<Void>deleteProduk(@PathVariable String idproduk){
        LOGGER.debug("DEBUG : delete produk by id");
        Produk produk = produkDAO.findOneProduk(Integer.parseInt(idproduk));
        if(produk == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        produkDAO.deleteProduk(produk);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
