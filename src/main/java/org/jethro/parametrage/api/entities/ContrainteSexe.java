package org.jethro.parametrage.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
//Aucune, Personne liée masculin/féminin, Sexes différents — cf. entities.enums.ContrainteSexe (enum miroir)
@Entity
@Table(name = "contraintesexe")
public class ContrainteSexe extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContrainteSexe contrainteSexe = (ContrainteSexe) o;
        return Objects.equals(libelle, contrainteSexe.libelle) && Objects.equals(description, contrainteSexe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }

    @Override
    public String toString() {
        return "ContrainteSexe{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
