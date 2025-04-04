package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VNiveauAcademiqueDao;
import org.jethro.parametrage.api.entities.views.VNiveauAcademique;
import org.jethro.parametrage.api.services.views.VNiveauAcademiqueService;

@ApplicationScoped
public class VNiveauAcademiqueServiceImpl implements VNiveauAcademiqueService {

  @Inject
  VNiveauAcademiqueDao quartierPersonDao;


  @Override
  public List<VNiveauAcademique> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VNiveauAcademique> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VNiveauAcademique touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VNiveauAcademique touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

