/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.controller;

import com.dicka.serverweb.serverweb.dao.ProductDAO;
import com.dicka.serverweb.serverweb.model.Product;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin(value = {"http://localhost:9000"})
public class ControllerTest {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(ControllerTest.class);
    
    private ProductDAO productDAO;
    
    @Inject
    public ControllerTest(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    
    @GetMapping(value = "/hallo")
    public String getHallo(){
        LOGGER.debug("DEBUG : HELLO WORLD");
        return "Hallo Controller";
    }
    
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        LOGGER.debug("DEBUG : LIST PRODUCT");
        List<Product> listproducts = productDAO.findAllProduct();
        if(listproducts.isEmpty()){
            LOGGER.debug("DEBUG : DATA IS EMPTY");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Product>>(listproducts, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertProduct")
    public ResponseEntity<Product>InsertProduct(@RequestBody Product product, UriComponentsBuilder builder){
        LOGGER.debug("DEBUG : INSERT PRODUCT");
        if(productDAO.ifExistProduct(product)){
            LOGGER.debug("DEBUG : CONFLICT");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productDAO.insertProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertProduct/{idproduct}")
                .buildAndExpand(product.getIdproduct()).toUri());
        return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteProduct/{idproduct}")
    public ResponseEntity<Void>deleteProduct(@PathVariable String idproduct){
        LOGGER.debug("DEBUG : DELETE PRODUCT");
        Product pp = productDAO.findOneProduct(Integer.parseInt(idproduct));
        productDAO.deleteProduct(pp);
        if(pp == null){
            LOGGER.debug("DEBUG : DATA IS EMPTY");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping(value = "/updateProduct/{idproduct}")
    public ResponseEntity<Product>UpdateProduct(@PathVariable String idproduct, @RequestBody Product product, 
            UriComponentsBuilder builder){
        LOGGER.debug("DEBUG : UPDATE PRODUCT");
        Product pp = productDAO.findOneProduct(Integer.parseInt(idproduct));
        if(pp == null){
            LOGGER.debug("DEBUG : DATA IS EMPTY");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        pp.setNama(product.getNama());
        pp.setDescription(product.getDescription());
        pp.setPrice(product.getPrice());
        pp.setVendor(product.getVendor());
        productDAO.updateProduct(pp);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updateProduct/{idproduct}")
                .buildAndExpand(product.getIdproduct()).toUri());
        return new ResponseEntity<>(pp, headers, HttpStatus.ACCEPTED);
    }
    
    @GetMapping(value = "/products/{idproduct}")
    public ResponseEntity<Product>findOneProduct(@PathVariable String idproduct){
        LOGGER.debug("DEBUG : FIND ONE PRODUCT");
        Product product = productDAO.findOneProduct(Integer.parseInt(idproduct));
        if(product == null){
            LOGGER.debug("DEBUG : DATA IS EMPTY");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
