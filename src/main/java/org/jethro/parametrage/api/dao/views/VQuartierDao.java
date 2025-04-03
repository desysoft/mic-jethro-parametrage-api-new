package org.jethro.parametrage.api.dao.views;

import java.util.List;
import org.jethro.parametrage.api.entities.views.VQuartier;

public interface VQuartierDao extends CRUDCommonView<VQuartier> {
  List<VQuartier> getByCommune(String idCommune, int pageIndex, int pageSize);
}
