package org.jethro.parametrage.api.dao;

import java.util.List;
import org.jethro.parametrage.api.exceptions.ProfessionCodeExistException;

public interface CRUDCommon<T> {

    List<T> getList();

    List<T> getList(int pageIndex, int pageSize);

    List<T> getList(String searchValue, int pageIndex, int pageSize);

    T findByIdCustom(String uuid);

    T findByCode(String code);

    Boolean isExistCode(String code);

    T save(T t);

    T update(String uuid,T t);

    boolean delete(String uuid);
}
