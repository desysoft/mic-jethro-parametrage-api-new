package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VSituationMatrimonialeDao;
import org.jethro.parametrage.api.entities.views.VSituationMatrimoniale;
import org.jethro.parametrage.api.services.views.VSituationMatrimonialeService;

@ApplicationScoped
public class VSituationMatrimonialeServiceImpl implements VSituationMatrimonialeService {

  @Inject
  VSituationMatrimonialeDao quartierPersonDao;


  @Override
  public List<VSituationMatrimoniale> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VSituationMatrimoniale> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VSituationMatrimoniale touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VSituationMatrimoniale touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

