package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.ProfessionDao;
import org.jethro.parametrage.api.entities.Profession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class ProfessionService implements BasicCommonService<Profession>{

     @Inject
     ProfessionDao professionDao;

     @Override
     public List<Profession> obtenirListe(int pageIndex, int pageSize) {
          System.out.println("obtenirListe");
          if(pageSize==0){
               return professionDao.getList();
          }else{
               return professionDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Profession> rechercher(String searchValue, int pageindex, int pageSize) {
          return professionDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Profession touverParId(String id) {
          return professionDao.findByIdCustom(id);
     }

     @Override
     public Profession touverParCode(String code) {
          return professionDao.findByCode(code);
     }

     @Override
     public Profession ajouter(Profession profession) {
          return professionDao.save(profession);
     }

     @Override
     public Profession modifer(String id, Profession profession) {
          return professionDao.update(id,profession);
     }

     @Override
     public Boolean supprimer(String id) {
          return professionDao.delete(id);
     }
}
