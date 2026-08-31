package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.SensDao;
import org.jethro.parametrage.api.entities.Sens;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class SensService implements BasicCommonService<Sens>{

     @Inject
     SensDao sensDao;

     @Override
     public List<Sens> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return sensDao.getList();
          }else{
               return sensDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Sens> rechercher(String searchValue, int pageindex, int pageSize) {
          return sensDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Sens touverParId(String id) {
          return sensDao.findByIdCustom(id);
     }

     @Override
     public Sens touverParCode(String code) {
          return sensDao.findByCode(code);
     }

     @Override
     public Sens ajouter(Sens sens) {
       try {
         return sensDao.save(sens);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public Sens modifer(String id, Sens sens) {
          return sensDao.update(id,sens);
     }

     @Override
     public Boolean supprimer(String id) {
          return sensDao.delete(id);
     }
}
