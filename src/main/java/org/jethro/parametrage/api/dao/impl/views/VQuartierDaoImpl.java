package org.jethro.parametrage.api.dao.impl.views;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jethro.parametrage.api.dao.views.CommonDaoForView;
import org.jethro.parametrage.api.dao.views.VQuartierDao;
import org.jethro.parametrage.api.entities.views.VQuartier;

@ApplicationScoped
public class VQuartierDaoImpl extends CommonDaoForView<VQuartier> implements VQuartierDao {

  @Override
  public List<VQuartier> getByCommune(String idCommune, int pageIndex, int pageSize){
    Map<String, Object> params = new HashMap<>();
    idCommune = checkAndGetForSearchValue(idCommune);
    params.put("idCommune", idCommune);
    PanacheQuery<VQuartier>
        panacheQuery =  find("id_commune LIKE ?1",idCommune);
    if(pageSize==0){
      return panacheQuery.list();
    }else {
      return panacheQuery.page(pageIndex,pageSize).list();
    }
  }

}
