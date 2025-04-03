package org.jethro.parametrage.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
//Marié, Célibataire, etc.
@Entity
@Table(name = "situationmatrimoniale")
public class SituationMatrimoniale extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SituationMatrimoniale situationMatrimoniale = (SituationMatrimoniale) o;
        return Objects.equals(libelle, situationMatrimoniale.libelle) && Objects.equals(description, situationMatrimoniale.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }

    @Override
    public String toString() {
        return "SituationMatrimoniale{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
