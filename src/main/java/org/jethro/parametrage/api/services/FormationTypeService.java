package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.entities.FormationType;
import org.jethro.parametrage.api.dao.FormationTypeDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class FormationTypeService implements BasicCommonService<FormationType>{

     @Inject
     FormationTypeDao formationTypeDao;

     @Override
     public List<FormationType> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return formationTypeDao.getList();
          }else{
               return formationTypeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<FormationType> rechercher(String searchValue, int pageindex, int pageSize) {
          return formationTypeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public FormationType touverParId(String id) {
          return formationTypeDao.findByIdCustom(id);
     }

     @Override
     public FormationType touverParCode(String code) {
          return formationTypeDao.findByCode(code);
     }

     @Override
     public FormationType ajouter(FormationType formationType) {
          return formationTypeDao.save(formationType);
     }

     @Override
     public FormationType modifer(String id, FormationType formationType) {
          return formationTypeDao.update(id,formationType);
     }

     @Override
     public Boolean supprimer(String id) {
          return formationTypeDao.delete(id);
     }
}
