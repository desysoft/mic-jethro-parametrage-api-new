package org.jethro.parametrage.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
//Ascendant, Descendant, Même génération, Union — cf. entities.enums.Sens (enum miroir)
@Entity
@Table(name = "sens")
public class Sens extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sens sens = (Sens) o;
        return Objects.equals(libelle, sens.libelle) && Objects.equals(description, sens.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }

    @Override
    public String toString() {
        return "Sens{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
