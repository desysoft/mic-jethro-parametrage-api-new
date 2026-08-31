package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.TypeFiliationDao;
import org.jethro.parametrage.api.entities.TypeFiliation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class TypeFiliationService implements BasicCommonService<TypeFiliation>{

     @Inject
     TypeFiliationDao typeFiliationDao;

     @Override
     public List<TypeFiliation> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return typeFiliationDao.getList();
          }else{
               return typeFiliationDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<TypeFiliation> rechercher(String searchValue, int pageindex, int pageSize) {
          return typeFiliationDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TypeFiliation touverParId(String id) {
          return typeFiliationDao.findByIdCustom(id);
     }

     @Override
     public TypeFiliation touverParCode(String code) {
          return typeFiliationDao.findByCode(code);
     }

     @Override
     public TypeFiliation ajouter(TypeFiliation typeFiliation) {
       try {
         return typeFiliationDao.save(typeFiliation);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public TypeFiliation modifer(String id, TypeFiliation typeFiliation) {
          return typeFiliationDao.update(id,typeFiliation);
     }

     @Override
     public Boolean supprimer(String id) {
          return typeFiliationDao.delete(id);
     }
}
