/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.controller;

import com.dicka.serverweb.serverweb.dao.GudangDAO;
import com.dicka.serverweb.serverweb.exception.AlreadyException;
import com.dicka.serverweb.serverweb.model.Gudang;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@RestController
@Service
@RequestMapping(value = "/api")
@CrossOrigin(value = {"http://localhost:9000"})
public class ControllerGudang {
    
    private GudangDAO gudangDAO;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerGudang.class);
    
    @Autowired
    public ControllerGudang(GudangDAO gudangDAO){
        this.gudangDAO = gudangDAO;
    }
    
    @GetMapping(value = "/gudangs")
    public ResponseEntity<List<Gudang>>GetListGudang(){
        LOGGER.debug("DEBUG : list gudang");
        List<Gudang> listgudang = gudangDAO.findAllGudang();
        if(listgudang.isEmpty()){
            LOGGER.debug("DEBUG : data tidak ada");
            //400 bad request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listgudang, HttpStatus.OK);
    }
    
    @GetMapping(value = "/gudangs/{idgudang}")
    public ResponseEntity<Gudang>GetGudang(@PathVariable String idgudang){
        LOGGER.debug("by igudang");
        Gudang gudang = gudangDAO.findOneGudangById(Integer.parseInt(idgudang));
        if(gudang == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertGudang")
    public ResponseEntity<Gudang>insertGudang(@RequestBody Gudang gudang, 
            UriComponentsBuilder builder){
        if(gudangDAO.ifGudangIsExist(gudang)){
            LOGGER.info("Data is already exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        gudangDAO.insertGudang(gudang);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertGudang/{idgudang}")
                .buildAndExpand(gudang.getIdgudang()).toUri());
        return new ResponseEntity<>(gudang, headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteGudang/{idgudang}")
    public ResponseEntity<Void>deleteGudang(@PathVariable String idgudang){
        LOGGER.info("delete gudang");
        Gudang gudang = gudangDAO.findOneGudangById(Integer.parseInt(idgudang));
        if(gudang == null){
            LOGGER.info("idgudang sudah ada");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        gudangDAO.deleteGudang(gudang);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}





