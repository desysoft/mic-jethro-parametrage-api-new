package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.entities.Ville;
import org.jethro.parametrage.api.dao.VilleDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class VilleService implements BasicCommonService<Ville>{

     @Inject
     VilleDao villeDao;

     @Override
     public List<Ville> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return villeDao.getList();
          }else{
               return villeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Ville> rechercher(String searchValue, int pageindex, int pageSize) {
          return villeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Ville touverParId(String id) {
          return villeDao.findByIdCustom(id);
     }

     @Override
     public Ville touverParCode(String code) {
          return villeDao.findByCode(code);
     }

     @Override
     public Ville ajouter(Ville ville) {
          return villeDao.save(ville);
     }

     @Override
     public Ville modifer(String id, Ville ville) {
          return villeDao.update(id,ville);
     }

     @Override
     public Boolean supprimer(String id) {
          return villeDao.delete(id);
     }
}
