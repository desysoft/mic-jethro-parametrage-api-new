package org.jethro.parametrage.api.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "quartier")
public class Neighborhood extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "id_commune", referencedColumnName = "id")
    public Commune commune;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighborhood neighborhood = (Neighborhood) o;
        return Objects.equals(code, neighborhood.code) && Objects.equals(libelle, neighborhood.libelle) && Objects.equals(description, neighborhood.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle, description);
    }

    @Override
    public String toString() {
        return "NeighborhoodDto{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", commune=" + commune +
                '}';
    }
}
