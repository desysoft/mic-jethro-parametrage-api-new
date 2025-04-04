package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VFiliereDao;
import org.jethro.parametrage.api.entities.views.VFiliere;
import org.jethro.parametrage.api.services.views.VFiliereService;

@ApplicationScoped
public class VFiliereServiceImpl implements VFiliereService {

  @Inject
  VFiliereDao vFiliereDao;


  @Override
  public List<VFiliere> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return vFiliereDao.getList();
    }else{
      return vFiliereDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VFiliere> rechercher(String searchValue, int pageindex, int pageSize) {
    return vFiliereDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VFiliere touverParId(String id) {
    return vFiliereDao.findByIdCustom(id);
  }

  @Override
  public VFiliere touverParCode(String code) {
    return vFiliereDao.findByCode(code);
  }
}
