package org.jethro.parametrage.api.services;

import org.jethro.parametrage.api.dao.CardinaliteDao;
import org.jethro.parametrage.api.entities.Cardinalite;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class CardinaliteService implements BasicCommonService<Cardinalite>{

     @Inject
     CardinaliteDao cardinaliteDao;

     @Override
     public List<Cardinalite> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return cardinaliteDao.getList();
          }else{
               return cardinaliteDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Cardinalite> rechercher(String searchValue, int pageindex, int pageSize) {
          return cardinaliteDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Cardinalite touverParId(String id) {
          return cardinaliteDao.findByIdCustom(id);
     }

     @Override
     public Cardinalite touverParCode(String code) {
          return cardinaliteDao.findByCode(code);
     }

     @Override
     public Cardinalite ajouter(Cardinalite cardinalite) {
       try {
         return cardinaliteDao.save(cardinalite);
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }

     @Override
     public Cardinalite modifer(String id, Cardinalite cardinalite) {
          return cardinaliteDao.update(id,cardinalite);
     }

     @Override
     public Boolean supprimer(String id) {
          return cardinaliteDao.delete(id);
     }
}
