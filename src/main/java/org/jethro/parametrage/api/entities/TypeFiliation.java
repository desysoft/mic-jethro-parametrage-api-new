package org.jethro.parametrage.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;
//Père, Mère, Frère, Sœur, Fils, Fille, Époux, Épouse, Apparenté(e)
@Entity
@Table(name = "type_filiation")
public class TypeFiliation extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "id_cardinalite", referencedColumnName = "id")
    public Cardinalite cardinalite;

    @ManyToOne
    @JoinColumn(name = "id_sens", referencedColumnName = "id")
    public Sens sens;

    @ManyToOne
    @JoinColumn(name = "id_contraintesexe", referencedColumnName = "id")
    public ContrainteSexe contrainteSexe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeFiliation that = (TypeFiliation) o;
        return Objects.equals(libelle, that.libelle) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }

    @Override
    public String toString() {
        return "TypeFiliation{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
