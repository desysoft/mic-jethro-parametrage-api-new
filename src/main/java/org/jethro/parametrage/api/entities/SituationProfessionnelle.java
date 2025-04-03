package org.jethro.parametrage.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "professionalCondition")
public class SituationProfessionnelle extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_professionalsector", referencedColumnName = "id")
    public TypeTravailleur typeTravailleur;

    @ManyToOne
    @JoinColumn(name = "id_job", referencedColumnName = "id")
    public Profession profession;

    @Column(name = "str_description")
    public String description;
    @Column(name = "id_person")
    public String idPerson;
}
