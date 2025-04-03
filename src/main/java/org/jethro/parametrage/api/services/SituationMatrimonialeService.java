package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.SituationMatrimonialeDao;
import org.jethro.parametrage.api.entities.SituationMatrimoniale;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class SituationMatrimonialeService implements BasicCommonService<SituationMatrimoniale>{

     @Inject
     SituationMatrimonialeDao situationMatrimonialeDao;

     @Override
     public List<SituationMatrimoniale> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return situationMatrimonialeDao.getList();
          }else{
               return situationMatrimonialeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<SituationMatrimoniale> rechercher(String searchValue, int pageindex, int pageSize) {
          return situationMatrimonialeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public SituationMatrimoniale touverParId(String id) {
          return situationMatrimonialeDao.findByIdCustom(id);
     }

     @Override
     public SituationMatrimoniale touverParCode(String code) {
          return situationMatrimonialeDao.findByCode(code);
     }

     @Override
     public SituationMatrimoniale ajouter(SituationMatrimoniale situationMatrimoniale) {
       try {
         return situationMatrimonialeDao.save(situationMatrimoniale);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public SituationMatrimoniale modifer(String id, SituationMatrimoniale situationMatrimoniale) {
          return situationMatrimonialeDao.update(id,situationMatrimoniale);
     }

     @Override
     public Boolean supprimer(String id) {
          return situationMatrimonialeDao.delete(id);
     }
}
