package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.DiplomeDao;
import org.jethro.parametrage.api.entities.Diplome;
import org.jethro.parametrage.api.services.DiplomeService;


@ApplicationScoped
public class DiplomeServiceImpl implements DiplomeService {

     @Inject
     DiplomeDao diplomeDao;

     @Override
     public List<Diplome> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return diplomeDao.getList();
          }else{
               return diplomeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Diplome> rechercher(String searchValue, int pageindex, int pageSize) {
          return diplomeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Diplome touverParId(String id) {
          return diplomeDao.findByIdCustom(id);
     }

     @Override
     public Diplome touverParCode(String code) {
          return diplomeDao.findByCode(code);
     }

     @Override
     public Diplome ajouter(Diplome diplome) {
       try {
         return diplomeDao.save(diplome);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public Diplome modifer(String id, Diplome diplome) {
          return diplomeDao.update(id,diplome);
     }

     @Override
     public Boolean supprimer(String id) {
          return diplomeDao.delete(id);
     }
}
