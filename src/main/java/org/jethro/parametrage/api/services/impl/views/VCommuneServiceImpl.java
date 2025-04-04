package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VCommuneDao;
import org.jethro.parametrage.api.entities.views.VCommune;
import org.jethro.parametrage.api.services.views.VCommuneService;

@ApplicationScoped
public class VCommuneServiceImpl implements VCommuneService {

  @Inject
  VCommuneDao quartierPersonDao;


  @Override
  public List<VCommune> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VCommune> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VCommune touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VCommune touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

