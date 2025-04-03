package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.FormationDao;
import org.jethro.parametrage.api.entities.Formation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class FormationService implements BasicCommonService<Formation>{

     @Inject
     FormationDao formationDao;

     @Override
     public List<Formation> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return formationDao.getList();
          }else{
               return formationDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Formation> rechercher(String searchValue, int pageindex, int pageSize) {
          return formationDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Formation touverParId(String id) {
          return formationDao.findByIdCustom(id);
     }

     @Override
     public Formation touverParCode(String code) {
          return formationDao.findByCode(code);
     }

     @Override
     public Formation ajouter(Formation formation) {
          return formationDao.save(formation);
     }

     @Override
     public Formation modifer(String id, Formation formation) {
          return formationDao.update(id,formation);
     }

     @Override
     public Boolean supprimer(String id) {
          return formationDao.delete(id);
     }
     
     public List<Formation> getFormationByTypeFormation(String idTypeFormation,int pageIndex, int pageSize){
          return formationDao.getByFormationType(idTypeFormation,pageIndex,pageSize);
     }
     


}
