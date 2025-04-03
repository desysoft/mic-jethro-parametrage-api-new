package org.jethro.parametrage.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseViewMapper<T,S> {
  S dtoByView(T t);

  default List<S> dtoByViewList(List<T> lst){
    return lst.stream()
        .map(this::dtoByView)
        .collect(Collectors.toList());
  }
}
