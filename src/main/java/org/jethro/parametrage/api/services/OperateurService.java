package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.entities.Operateur;
import org.jethro.parametrage.api.dao.OperateurDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class OperateurService implements BasicCommonService<Operateur>{

     @Inject
     OperateurDao operateurDao;

     @Override
     public List<Operateur> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return operateurDao.getList();
          }else{
               return operateurDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Operateur> rechercher(String searchValue, int pageindex, int pageSize) {
          return operateurDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Operateur touverParId(String id) {
          return operateurDao.findByIdCustom(id);
     }

     @Override
     public Operateur touverParCode(String code) {
          return operateurDao.findByCode(code);
     }

     @Override
     public Operateur ajouter(Operateur operateur) {
          return operateurDao.save(operateur);
     }

     @Override
     public Operateur modifer(String id, Operateur operateur) {
          return operateurDao.update(id,operateur);
     }

     @Override
     public Boolean supprimer(String id) {
          return operateurDao.delete(id);
     }
}
