package org.jethro.parametrage.api.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "formation")
public class Formation extends BaseEntity{

    @Column(name = "str_name")
    public String libelle;

    @Column(name = "str_descritption")
    public String description;

    @ManyToOne
    @JoinColumn(name = "id_formationtype", referencedColumnName = "id")
    public FormationType formationType;

    @Override
    public String toString() {
        return "Formation{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", formationType=" + formationType +
                '}';
    }
}
