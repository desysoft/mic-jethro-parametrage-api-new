package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VTrancheAgeDao;
import org.jethro.parametrage.api.entities.views.VTrancheAge;
import org.jethro.parametrage.api.services.views.VTrancheAgeService;

@ApplicationScoped
public class VTrancheAgeServiceImpl implements VTrancheAgeService {

  @Inject
  VTrancheAgeDao quartierPersonDao;


  @Override
  public List<VTrancheAge> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VTrancheAge> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VTrancheAge touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VTrancheAge touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

