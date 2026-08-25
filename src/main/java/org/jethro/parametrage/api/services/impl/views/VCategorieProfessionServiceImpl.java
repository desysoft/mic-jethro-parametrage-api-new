package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VCategorieProfessionDao;
import org.jethro.parametrage.api.entities.views.VCategorieProfession;
import org.jethro.parametrage.api.services.views.VCategorieProfessionService;

@ApplicationScoped
public class VCategorieProfessionServiceImpl implements VCategorieProfessionService {

  @Inject
  VCategorieProfessionDao categorieProfessionDao;

  @Override
  public List<VCategorieProfession> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return categorieProfessionDao.getList();
    }else{
      return categorieProfessionDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VCategorieProfession> rechercher(String searchValue, int pageindex, int pageSize) {
    return categorieProfessionDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VCategorieProfession touverParId(String id) {
    return categorieProfessionDao.findByIdCustom(id);
  }

  @Override
  public VCategorieProfession touverParCode(String code) {
    return categorieProfessionDao.findByCode(code);
  }
}
