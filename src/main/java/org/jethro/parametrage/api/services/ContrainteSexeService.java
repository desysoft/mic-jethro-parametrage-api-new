package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.ContrainteSexeDao;
import org.jethro.parametrage.api.entities.ContrainteSexe;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class ContrainteSexeService implements BasicCommonService<ContrainteSexe>{

     @Inject
     ContrainteSexeDao contrainteSexeDao;

     @Override
     public List<ContrainteSexe> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return contrainteSexeDao.getList();
          }else{
               return contrainteSexeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<ContrainteSexe> rechercher(String searchValue, int pageindex, int pageSize) {
          return contrainteSexeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public ContrainteSexe touverParId(String id) {
          return contrainteSexeDao.findByIdCustom(id);
     }

     @Override
     public ContrainteSexe touverParCode(String code) {
          return contrainteSexeDao.findByCode(code);
     }

     @Override
     public ContrainteSexe ajouter(ContrainteSexe contrainteSexe) {
       try {
         return contrainteSexeDao.save(contrainteSexe);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public ContrainteSexe modifer(String id, ContrainteSexe contrainteSexe) {
          return contrainteSexeDao.update(id,contrainteSexe);
     }

     @Override
     public Boolean supprimer(String id) {
          return contrainteSexeDao.delete(id);
     }
}
