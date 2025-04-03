package org.jethro.parametrage.api.entities;


import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "diplome")
public class Diplome extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diplome that = (Diplome) o;
        return Objects.equals(code, that.code) && Objects.equals(libelle, that.libelle) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle, description);
    }

    @Override
    public String toString() {
        return "Diplome{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
