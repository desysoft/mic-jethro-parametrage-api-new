package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VTypeTravailleurDao;
import org.jethro.parametrage.api.entities.views.VTypeTravailleur;
import org.jethro.parametrage.api.services.views.VTypeTravailleurService;

@ApplicationScoped
public class VTypeTravailleurServiceImpl implements VTypeTravailleurService {

  @Inject
  VTypeTravailleurDao quartierPersonDao;


  @Override
  public List<VTypeTravailleur> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VTypeTravailleur> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VTypeTravailleur touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VTypeTravailleur touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

