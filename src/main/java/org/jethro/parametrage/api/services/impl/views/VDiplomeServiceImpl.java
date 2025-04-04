package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VDiplomeDao;
import org.jethro.parametrage.api.entities.views.VDiplome;
import org.jethro.parametrage.api.services.views.VDiplomeService;

@ApplicationScoped
public class VDiplomeServiceImpl implements VDiplomeService {

  @Inject
  VDiplomeDao quartierPersonDao;


  @Override
  public List<VDiplome> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VDiplome> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VDiplome touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VDiplome touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

