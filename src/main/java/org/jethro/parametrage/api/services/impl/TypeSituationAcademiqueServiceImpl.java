package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.TypeSituationAcademiqueDao;
import org.jethro.parametrage.api.entities.TypeSituationAcademique;
import org.jethro.parametrage.api.services.TypeSituationAcademiqueService;


@ApplicationScoped
public class TypeSituationAcademiqueServiceImpl implements TypeSituationAcademiqueService {

     @Inject
     TypeSituationAcademiqueDao typeSituationAcademiqueDao;

     @Override
     public List<TypeSituationAcademique> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return typeSituationAcademiqueDao.getList();
          }else{
               return typeSituationAcademiqueDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<TypeSituationAcademique> rechercher(String searchValue, int pageindex, int pageSize) {
          return typeSituationAcademiqueDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public TypeSituationAcademique touverParId(String id) {
          return typeSituationAcademiqueDao.findByIdCustom(id);
     }

     @Override
     public TypeSituationAcademique touverParCode(String code) {
          return typeSituationAcademiqueDao.findByCode(code);
     }

     @Override
     public TypeSituationAcademique ajouter(TypeSituationAcademique typeSituationAcademique) {
       try {
         return typeSituationAcademiqueDao.save(typeSituationAcademique);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public TypeSituationAcademique modifer(String id, TypeSituationAcademique typeSituationAcademique) {
          return typeSituationAcademiqueDao.update(id,typeSituationAcademique);
     }

     @Override
     public Boolean supprimer(String id) {
          return typeSituationAcademiqueDao.delete(id);
     }
}
