package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.jethro.parametrage.api.dao.FiliereDao;
import org.jethro.parametrage.api.entities.Filiere;
import org.jethro.parametrage.api.services.FiliereService;


@ApplicationScoped
public class FiliereServiceImpl implements FiliereService {

     @Inject
     FiliereDao filiereDao;

     @Override
     public List<Filiere> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return filiereDao.getList();
          }else{
               return filiereDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Filiere> rechercher(String searchValue, int pageindex, int pageSize) {
          return filiereDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Filiere touverParId(String id) {
          return filiereDao.findByIdCustom(id);
     }

     @Override
     public Filiere touverParCode(String code) {
          return filiereDao.findByCode(code);
     }

     @Override
     public Filiere ajouter(Filiere filiere) {
       try {
         return filiereDao.save(filiere);
       } catch (Exception e) {
         throw new WebApplicationException(e);
       }
     }

     @Override
     public Filiere modifer(String id, Filiere filiere) {
          return filiereDao.update(id,filiere);
     }

     @Override
     public Boolean supprimer(String id) {
          return filiereDao.delete(id);
     }
}
