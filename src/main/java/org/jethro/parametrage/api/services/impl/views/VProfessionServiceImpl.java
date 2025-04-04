package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VProfessionDao;
import org.jethro.parametrage.api.entities.views.VProfession;
import org.jethro.parametrage.api.services.views.VProfessionService;

@ApplicationScoped
public class VProfessionServiceImpl implements VProfessionService {

  @Inject
  VProfessionDao quartierPersonDao;


  @Override
  public List<VProfession> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VProfession> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VProfession touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VProfession touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}
