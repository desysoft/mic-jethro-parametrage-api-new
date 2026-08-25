package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.CategorieProfessionDao;
import org.jethro.parametrage.api.entities.CategorieProfession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class CategorieProfessionService implements BasicCommonService<CategorieProfession>{

     @Inject
     CategorieProfessionDao categorieProfessionDao;

     @Override
     public List<CategorieProfession> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return categorieProfessionDao.getList();
          }else{
               return categorieProfessionDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<CategorieProfession> rechercher(String searchValue, int pageindex, int pageSize) {
          return categorieProfessionDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public CategorieProfession touverParId(String id) {
          return categorieProfessionDao.findByIdCustom(id);
     }

     @Override
     public CategorieProfession touverParCode(String code) {
          return categorieProfessionDao.findByCode(code);
     }

     @Override
     public CategorieProfession ajouter(CategorieProfession categorieProfession) {
          return categorieProfessionDao.save(categorieProfession);
     }

     @Override
     public CategorieProfession modifer(String id, CategorieProfession categorieProfession) {
          return categorieProfessionDao.update(id,categorieProfession);
     }

     @Override
     public Boolean supprimer(String id) {
          return categorieProfessionDao.delete(id);
     }
}
