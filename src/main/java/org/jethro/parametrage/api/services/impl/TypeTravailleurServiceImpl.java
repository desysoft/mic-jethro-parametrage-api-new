package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.TypeTravailleurDao;
import org.jethro.parametrage.api.entities.TypeTravailleur;
import org.jethro.parametrage.api.services.TypeTravailleurService;


@ApplicationScoped
public class TypeTravailleurServiceImpl implements TypeTravailleurService {

     @Inject
     TypeTravailleurDao typeTravailleurDao;

     @Override
     public List<TypeTravailleur> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return typeTravailleurDao.getList();
          }else{
               return typeTravailleurDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<TypeTravailleur> rechercher(String searchValue, int pageindex, int pageSize) {
          return typeTravailleurDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TypeTravailleur touverParId(String id) {
          return typeTravailleurDao.findByIdCustom(id);
     }

     @Override
     public TypeTravailleur touverParCode(String code) {
          return typeTravailleurDao.findByCode(code);
     }

     @Override
     public TypeTravailleur ajouter(TypeTravailleur typeTravailleur) {
       try {
         return typeTravailleurDao.save(typeTravailleur);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public TypeTravailleur modifer(String id, TypeTravailleur typeTravailleur) {
          return typeTravailleurDao.update(id,typeTravailleur);
     }

     @Override
     public Boolean supprimer(String id) {
          return typeTravailleurDao.delete(id);
     }
}
