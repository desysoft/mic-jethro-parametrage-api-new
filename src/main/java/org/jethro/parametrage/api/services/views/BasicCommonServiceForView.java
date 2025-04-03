package org.jethro.parametrage.api.services.views;

import java.util.List;

public interface BasicCommonServiceForView<T> {

    List<T> obtenirListe(int pageIndex,int pageSize);

    List<T> rechercher(String searchValue,int pageindex,int pageSize);

    T touverParId(String id);

    T touverParCode(String code);
}
