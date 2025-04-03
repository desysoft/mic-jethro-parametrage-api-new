package org.jethro.parametrage.api.dao.views;

import java.util.List;

public interface CRUDCommonView<T> {

    List<T> getList();

    List<T> getList(int pageIndex, int pageSize);

    List<T> getList(String searchValue, int pageIndex, int pageSize);

    T findByIdCustom(String uuid);

    T findByCode(String code);
}
