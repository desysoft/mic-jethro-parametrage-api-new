package org.jethro.parametrage.api.services;

import java.util.List;

public interface BasicCommonService<T> {

    List<T> obtenirListe(int pageIndex,int pageSize);

    List<T> rechercher(String searchValue,int pageindex,int pageSize);

    T touverParId(String id);

    T touverParCode(String code);

    T ajouter(T t);

    T modifer(String id,T t);

    Boolean supprimer(String id);
}
