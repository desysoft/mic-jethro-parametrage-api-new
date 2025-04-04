package org.jethro.parametrage.api.services.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dao.views.VTypeSituationAcademiqueDao;
import org.jethro.parametrage.api.entities.views.VTypeSituationAcademique;
import org.jethro.parametrage.api.services.views.VTypeSituationAcademiqueService;

@ApplicationScoped
public class VTypeSituationAcademiqueServiceImpl implements VTypeSituationAcademiqueService {

  @Inject
  VTypeSituationAcademiqueDao quartierPersonDao;


  @Override
  public List<VTypeSituationAcademique> obtenirListe(int pageIndex, int pageSize) {
    if(pageSize==0){
      return quartierPersonDao.getList();
    }else{
      return quartierPersonDao.getList(pageIndex,pageSize);
    }
  }

  @Override
  public List<VTypeSituationAcademique> rechercher(String searchValue, int pageindex, int pageSize) {
    return quartierPersonDao.getList(searchValue, pageindex,pageSize);
  }

  @Override
  public VTypeSituationAcademique touverParId(String id) {
    return quartierPersonDao.findByIdCustom(id);
  }

  @Override
  public VTypeSituationAcademique touverParCode(String code) {
    return quartierPersonDao.findByCode(code);
  }
}

