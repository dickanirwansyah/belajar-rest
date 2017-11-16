/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.exception.AlreadyException;
import com.dicka.serverweb.serverweb.model.Gudang;
import com.dicka.serverweb.serverweb.repository.RepositoryGudang;
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
@Validated
@Service
public class GudangDAOImpl implements GudangDAO{
    
    private RepositoryGudang repositoryGudang;
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(ProductDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    public GudangDAOImpl(RepositoryGudang repositoryGudang){
        this.repositoryGudang = repositoryGudang;
    }
    
    @Override
    public Gudang insertGudang(Gudang gudang) {
      LOGGER.debug("insert gudang");
      Gudang existing = repositoryGudang.findOne(gudang.getIdgudang());
      if(existing != null){
          LOGGER.debug("data is already exist");
          throw new AlreadyException(String.format("data sudah ada", gudang.getIdgudang()));
      }
      return repositoryGudang.save(gudang);
    }

    @Override
    public Gudang updateGudang(Gudang gudang) {
       LOGGER.info("INFO : update gudang");
       if(!entityManager.contains(gudang))
           gudang = entityManager.merge(gudang);
       return gudang;
    }

    @Override
    public void deleteGudang(Gudang gudang) {
      LOGGER.info("INFO : delete gudang");
      repositoryGudang.delete(gudang);
    }

    @Override
    public Gudang findOneGudangById(int idgudang) {
        LOGGER.debug("DEBUG : findone gudang");
        return repositoryGudang.findOne(idgudang);
    }

    @Override
    public List<Gudang> findAllGudang() {
      LOGGER.debug("DEBUG : list gudang");
      return repositoryGudang.findAll();
    }

    @Override
    public boolean ifGudangIsExist(Gudang gudang) {
      LOGGER.info("INFO : gudang sudah ada");
      return findOneGudangById(gudang.getIdgudang()) != null;
    }
    
}
