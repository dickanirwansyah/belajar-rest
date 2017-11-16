/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.serverweb.serverweb.dao;

import com.dicka.serverweb.serverweb.exception.AlreadyException;
import com.dicka.serverweb.serverweb.model.Kategori;
import com.dicka.serverweb.serverweb.repository.RepositoryKategori;
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
public class KategoriDAOImpl implements KategoriDAO{
    
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(KategoriDAOImpl.class);
    
    private RepositoryKategori repositoryKategori;
    
    @Inject
    public KategoriDAOImpl(RepositoryKategori repositoryKategori){
        this.repositoryKategori = repositoryKategori;
    }
    
    @Override
    public Kategori insertKategori(Kategori kategori) {
        LOGGER.debug("DEBUG : insert kategori");
      Kategori ifExisting = repositoryKategori.findOne(kategori.getIdkategori());
      if(ifExisting != null){
          throw new AlreadyException(String.format("idkategori duplicated", kategori.getIdkategori()));
      }
      return repositoryKategori.save(kategori);
    }

    @Override
    public Kategori updateKategori(Kategori kategori) {
       LOGGER.debug("DEBUG : update");
      if(!entityManager.contains(kategori))
          kategori = entityManager.merge(kategori);
      return kategori;
    }

    @Override
    public void deleteKategori(Kategori kategori) {
      repositoryKategori.delete(kategori);
    }

    
    @Override
    public List<Kategori> findAllKategori() {
      LOGGER.debug("DEBUG : list kategori");
      return repositoryKategori.findAll();
    }

    @Override
    public Kategori findOneKategori(int idkategori) {
      LOGGER.debug("DEBUG : findone kategori");
      return repositoryKategori.findOne(idkategori);
    }

    @Override
    public boolean ifKategoriIsExist(Kategori kategori) {
        return findOneKategori(kategori.getIdkategori())!=null;
    }
    
}
