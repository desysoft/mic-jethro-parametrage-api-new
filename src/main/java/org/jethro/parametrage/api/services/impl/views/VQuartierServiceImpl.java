package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VQuartierDao;
import org.jethro.parametrage.api.entities.views.VQuartier;
import org.jethro.parametrage.api.services.views.VQuartierService;

@ApplicationScoped
public class VQuartierServiceImpl implements VQuartierService {

  @Inject
  VQuartierDao quartierPersonDao;


  @Override
  public List<VQuartier> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VQuartier> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VQuartier touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VQuartier touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}
