package org.jethro.parametrage.api.dto;

import java.util.Objects;

public class SituationMatrimoniale extends BaseEntityDTO {

    private String libelle;
    private String description;

    public SituationMatrimoniale(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
