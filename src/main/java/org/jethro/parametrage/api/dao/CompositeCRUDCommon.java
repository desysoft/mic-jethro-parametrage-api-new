package org.jethro.parametrage.api.dao;

import java.util.List;

public interface CompositeCRUDCommon<T> extends CRUDCommon<T> {
  List<T> getListForCompositeTable(String searchValue, int pageIndex, int pageSize);
}
