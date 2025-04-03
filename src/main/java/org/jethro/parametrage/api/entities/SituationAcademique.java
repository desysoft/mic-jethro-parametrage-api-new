package org.jethro.parametrage.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "academiquecondition")
public class SituationAcademique extends BaseEntity{
  @ManyToOne
  @JoinColumn(name = "id_type_situation_academique", referencedColumnName = "id")
  public TypeSituationAcademique typeSituationAcademique;
  @ManyToOne
  @JoinColumn(name = "id_filiere", referencedColumnName = "id")
  public Filiere filiere;
  @ManyToOne
  @JoinColumn(name = "id_diplome", referencedColumnName = "id")
  public Diplome diplome;

  @ManyToOne
  @JoinColumn(name = "id_niveau_academique", referencedColumnName = "id")
  public NiveauAcademique niveauAcademique;

  @Column(name = "id_person")
  public String idPerson;

}
