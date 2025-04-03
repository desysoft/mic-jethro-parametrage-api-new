package org.jethro.parametrage.api.dao.views;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jethro.parametrage.api.dao.AbstractDao;
import org.jethro.parametrage.api.entities.views.BaseEntityForView;
import org.jethro.parametrage.api.tools.ParametersConfig;

public class CommonDaoForView<T extends BaseEntityForView> extends AbstractDao
    implements PanacheRepositoryBase<T, String> {

  public static final Logger LOG = Logger.getLogger("CommonDao");

  public List<T> getList() {
    LOG.info("CommonDao +++ getList");
    return listAll();
  }

  public List<T> getList(int pageIndex, int pageSize) {
    return findAll()
        .page(pageIndex, pageSize)
        .list();
  }

  public List<T> getList(String searchValue, int pageIndex, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    searchValue = checkAndGetForSearchValue(searchValue);
    params.put("searchValue", searchValue);
    PanacheQuery<T> panacheQuery = find(
        "LOWER(code) LIKE LOWER(:searchValue) OR LOWER(libelle) LIKE LOWER(:searchValue) OR LOWER" +
            "(description) LIKE LOWER(:searchValue)",
        params);
    if (pageSize == 0) {
      return panacheQuery.list();
    } else {
      return panacheQuery.page(pageIndex, pageSize).list();
    }
  }

  public T findByIdCustom(String uuid) {
    try {
      return this.find("uuid = ?1", uuid).firstResult();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  public T findByCode(String code) {
    try {
      return this.find("code = ?1", code).firstResult();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  public Boolean isExistCode(String code) {
    return findByCode(code) != null;
  }
}
