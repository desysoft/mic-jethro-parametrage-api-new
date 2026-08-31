package org.jethro.parametrage.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
//Unique, Multiple — cf. entities.enums.Cardinalite (enum miroir)
@Entity
@Table(name = "cardinalite")
public class Cardinalite extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardinalite cardinalite = (Cardinalite) o;
        return Objects.equals(libelle, cardinalite.libelle) && Objects.equals(description, cardinalite.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }

    @Override
    public String toString() {
        return "Cardinalite{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
