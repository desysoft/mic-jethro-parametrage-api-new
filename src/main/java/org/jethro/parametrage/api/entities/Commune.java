package org.jethro.parametrage.api.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commune")
public class Commune extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "id_ville", referencedColumnName = "id")
    public Ville ville;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commune commune = (Commune) o;
        return Objects.equals(libelle, commune.libelle) && Objects.equals(description, commune.description) && Objects.equals(ville, commune.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description, ville);
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", ville=" + ville +
                '}';
    }
}
