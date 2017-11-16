/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.exception.AlreadyException;
import com.dicka.serverweb.serverweb.model.Product;
import com.dicka.serverweb.serverweb.repository.RepositoryProduct;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author java-spring
 */
@Transactional
@Service
@Validated
public class ProductDAOImpl implements ProductDAO{
    
    @PersistenceContext
    private EntityManager entityManager;

    private RepositoryProduct repositoryProduct;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);
    
    @Inject
    public ProductDAOImpl(RepositoryProduct repositoryProduct){
        this.repositoryProduct = repositoryProduct;
    }
    
    @Override
    public Product insertProduct(Product product) {
      LOGGER.debug("DEBUG : INSERT");
      Product existing = repositoryProduct.findOne(product.getIdproduct());
      if(existing!=null){
          throw new AlreadyException(String.format("code duplicated !", product.getIdproduct()));
      }
      return repositoryProduct.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
      LOGGER.debug("DEBUG : UPDATE");
      if(!entityManager.contains(product))
          product = entityManager.merge(product);
      return product;
    }

    @Override
    public void deleteProduct(Product product) {
      LOGGER.debug("DEBUG : DELETE");
      repositoryProduct.delete(product);
    }

    @Override
    public Product findOneProduct(int idproduct) {
      LOGGER.debug("DEBUG : FIND ONE BY ID");
      return repositoryProduct.findOne(idproduct);
    }

    @Override
    public List<Product> findAllProduct() {
     LOGGER.debug("DEBUG : FIND ALL LIST");
     return repositoryProduct.findAll();
    }

    @Override
    public boolean ifExistProduct(Product product) {
     return findOneProduct(product.getIdproduct()) != null;
    }
    
}
