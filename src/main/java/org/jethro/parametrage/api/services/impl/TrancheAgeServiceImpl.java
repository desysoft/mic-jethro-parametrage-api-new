package org.jethro.parametrage.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.TrancheAgeDao;
import org.jethro.parametrage.api.entities.TrancheAge;
import org.jethro.parametrage.api.services.TrancheAgeService;


@ApplicationScoped
public class TrancheAgeServiceImpl implements TrancheAgeService {

     private static Logger LOG = Logger.getLogger(TrancheAgeServiceImpl.class.getName());

     @Inject
     TrancheAgeDao trancheAgeDao;

     @Override
     public List<TrancheAge> obtenirListe(int pageIndex, int pageSize) {
          List<TrancheAge> lst = new ArrayList<>();
          if(pageSize==0){
               lst = trancheAgeDao.getList();
               LOG.info("Taille de l'liste : " + lst.size());
          }else{
               lst =  trancheAgeDao.getList(pageIndex,pageSize);
               LOG.info("Taille de l'liste : " + lst.size());
          }
          return lst;
     }

     @Override
     public List<TrancheAge> rechercher(String searchValue, int pageindex, int pageSize) {
          return trancheAgeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TrancheAge touverParId(String id) {
          return trancheAgeDao.findByIdCustom(id);
     }

     @Override
     public TrancheAge touverParCode(String code) {
          return trancheAgeDao.findByCode(code);
     }

     @Override
     public TrancheAge ajouter(TrancheAge trancheAge) {
       try {
         return trancheAgeDao.save(trancheAge);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public TrancheAge modifer(String id, TrancheAge trancheAge) {
          return trancheAgeDao.update(id,trancheAge);
     }

     @Override
     public Boolean supprimer(String id) {
          return trancheAgeDao.delete(id);
     }
}
