package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.TypeContactDao;
import org.jethro.parametrage.api.entities.TypeContact;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class TypeContactService implements BasicCommonService<TypeContact>{

     @Inject
     TypeContactDao typeContactDao;

     @Override
     public List<TypeContact> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return typeContactDao.getList();
          }else{
               return typeContactDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<TypeContact> rechercher(String searchValue, int pageindex, int pageSize) {
          return typeContactDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TypeContact touverParId(String id) {
          return typeContactDao.findByIdCustom(id);
     }

     @Override
     public TypeContact touverParCode(String code) {
          return typeContactDao.findByCode(code);
     }

     @Override
     public TypeContact ajouter(TypeContact typeContact) {
          return typeContactDao.save(typeContact);
     }

     @Override
     public TypeContact modifer(String id, TypeContact typeContact) {
          return typeContactDao.update(id,typeContact);
     }

     @Override
     public Boolean supprimer(String id) {
          return typeContactDao.delete(id);
     }
}
