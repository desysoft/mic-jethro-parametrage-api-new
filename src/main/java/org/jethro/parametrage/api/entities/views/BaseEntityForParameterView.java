package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityForParameterView extends BaseEntityForView{

  @Column(name = "nombre_hommes")
  public Integer nombreHomme;

  @Column(name = "nombre_femmes")
  public Integer nombreFemme;

  @Column(name = "total_personnes")
  public Integer totalPersons;

}
