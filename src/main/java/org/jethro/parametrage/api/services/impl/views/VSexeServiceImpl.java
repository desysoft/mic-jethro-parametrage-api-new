package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VSexeDao;
import org.jethro.parametrage.api.entities.views.VSexe;
import org.jethro.parametrage.api.services.views.VSexeService;

@ApplicationScoped
public class VSexeServiceImpl implements VSexeService {

  @Inject
  VSexeDao quartierPersonDao;


  @Override
  public List<VSexe> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VSexe> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VSexe touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VSexe touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

