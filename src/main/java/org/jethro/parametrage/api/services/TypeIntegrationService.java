package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.TypeIntegrationDao;
import org.jethro.parametrage.api.entities.TypeIntegration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class TypeIntegrationService implements BasicCommonService<TypeIntegration>{

     @Inject
     TypeIntegrationDao typeIntegrationDao;

     @Override
     public List<TypeIntegration> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return typeIntegrationDao.getList();
          }else{
               return typeIntegrationDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<TypeIntegration> rechercher(String searchValue, int pageindex, int pageSize) {
          return typeIntegrationDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TypeIntegration touverParId(String id) {
          return typeIntegrationDao.findByIdCustom(id);
     }

     @Override
     public TypeIntegration touverParCode(String code) {
          return typeIntegrationDao.findByCode(code);
     }

     @Override
     public TypeIntegration ajouter(TypeIntegration typeIntegration) {
          return typeIntegrationDao.save(typeIntegration);
     }

     @Override
     public TypeIntegration modifer(String id, TypeIntegration typeIntegration) {
          return typeIntegrationDao.update(id,typeIntegration);
     }

     @Override
     public Boolean supprimer(String id) {
          return typeIntegrationDao.delete(id);
     }
}
