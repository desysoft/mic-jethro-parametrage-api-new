package org.jethro.parametrage.api.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matrimonialinfo")
public class MatrimonialInfos extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "id_situationmatrimoniale", referencedColumnName = "id")
  public SituationMatrimoniale situationMatrimoniale;

  @Column(name = "isspousechristian")
  public boolean isSpouseChristian;

  @Column(name = "weedingdate")
  public LocalDate weedingDate;

  @Column(name = "numberofchild")
  public int numberOfChild = 0;

}
