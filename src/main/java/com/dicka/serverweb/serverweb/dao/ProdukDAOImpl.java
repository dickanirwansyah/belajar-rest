/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.exception.AlreadyException;
import com.dicka.serverweb.serverweb.model.Produk;
import com.dicka.serverweb.serverweb.repository.RepositoryProduk;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author java-spring
 */
@Validated
@Transactional
@Service
public class ProdukDAOImpl implements ProdukDAO{

    private RepositoryProduk repositoryProduk;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public ProdukDAOImpl(RepositoryProduk repositoryProduk){
        this.repositoryProduk = repositoryProduk;
    }
    
    @Override
    public List<Produk> listproduk() {
      return repositoryProduk.findAll();
    }

    @Override
    public Produk findOneProduk(int idproduk) {
     return repositoryProduk.findOne(idproduk);
    }

    @Override
    public Produk insertProduk(Produk produk) {
      Produk existing = repositoryProduk.findOne(produk.getIdproduk());
      if(existing != null){
          throw new AlreadyException(String.format("idproduk sudah ada", produk.getIdproduk()));
      }
      return repositoryProduk.save(produk);
    }

    @Override
    public Produk updateProduk(Produk produk) {
      if(!entityManager.contains(produk))
          produk = entityManager.merge(produk);
      return produk;
    }

    @Override
    public void deleteProduk(Produk produk) {
      repositoryProduk.delete(produk);
    }

    @Override
    public boolean ifProdukIsExist(Produk produk) {
       return findOneProduk(produk.getIdproduk()) != null;
    }
    
}
