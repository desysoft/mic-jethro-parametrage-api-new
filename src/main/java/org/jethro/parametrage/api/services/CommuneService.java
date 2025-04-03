package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.dao.CommuneDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class CommuneService implements BasicCommonService<Commune>{

     @Inject
     CommuneDao communeDao;

     @Override
     public List<Commune> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return communeDao.getList();
          }else{
               return communeDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Commune> rechercher(String searchValue, int pageindex, int pageSize) {
          return communeDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Commune touverParId(String id) {
          return communeDao.findByIdCustom(id);
     }

     @Override
     public Commune touverParCode(String code) {
          return communeDao.findByCode(code);
     }

     @Override
     public Commune ajouter(Commune commune) {
          return communeDao.save(commune);
     }

     @Override
     public Commune modifer(String id, Commune commune) {
          return communeDao.update(id,commune);
     }

     @Override
     public Boolean supprimer(String id) {
          return communeDao.delete(id);
     }


     public List<Commune> obtenirListeDesCommunesParVille(String idVille,int pageIndex, int pageSize){
          return communeDao.getByVille(idVille,pageIndex,pageSize);
     }


}
