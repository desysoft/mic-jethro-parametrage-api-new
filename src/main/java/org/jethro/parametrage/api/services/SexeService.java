package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.entities.Sexe;
import org.jethro.parametrage.api.dao.SexeDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class SexeService implements BasicCommonService<Sexe>{

     @Inject
     SexeDao sexeDao;

     @Override
     public List<Sexe> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return sexeDao.getList();
          }else{
               return sexeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Sexe> rechercher(String searchValue, int pageindex, int pageSize) {
          return sexeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Sexe touverParId(String id) {
          return sexeDao.findByIdCustom(id);
     }

     @Override
     public Sexe touverParCode(String code) {
          return sexeDao.findByCode(code);
     }

     @Override
     public Sexe ajouter(Sexe sexe) {
          return sexeDao.save(sexe);
     }

     @Override
     public Sexe modifer(String id, Sexe sexe) {
          return sexeDao.update(id,sexe);
     }

     @Override
     public Boolean supprimer(String id) {
          return sexeDao.delete(id);
     }
}
