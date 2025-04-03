package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.NiveauAcademiqueDao;
import org.jethro.parametrage.api.entities.NiveauAcademique;
import org.jethro.parametrage.api.services.NiveauAcademiqueService;


@ApplicationScoped
public class NiveauAcademiqueServiceImpl implements NiveauAcademiqueService {

     @Inject
     NiveauAcademiqueDao niveauAcademiqueDao;

     @Override
     public List<NiveauAcademique> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return niveauAcademiqueDao.getList();
          }else{
               return niveauAcademiqueDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<NiveauAcademique> rechercher(String searchValue, int pageindex, int pageSize) {
          return niveauAcademiqueDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public NiveauAcademique touverParId(String id) {
          return niveauAcademiqueDao.findByIdCustom(id);
     }

     @Override
     public NiveauAcademique touverParCode(String code) {
          return niveauAcademiqueDao.findByCode(code);
     }

     @Override
     public NiveauAcademique ajouter(NiveauAcademique niveauAcademique) {
       try {
         return niveauAcademiqueDao.save(niveauAcademique);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public NiveauAcademique modifer(String id, NiveauAcademique niveauAcademique) {
          return niveauAcademiqueDao.update(id,niveauAcademique);
     }

     @Override
     public Boolean supprimer(String id) {
          return niveauAcademiqueDao.delete(id);
     }
}
