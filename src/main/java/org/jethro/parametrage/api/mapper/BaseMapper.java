package org.jethro.parametrage.api.mapper;


import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<T,S>{
  S toDto(T t);
  T toEntity(S s);

  default List<S> toDtoList(List<T> lst){
    return lst.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  default List<T> toEntityList(List<S> lst){
    return lst.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }
}
