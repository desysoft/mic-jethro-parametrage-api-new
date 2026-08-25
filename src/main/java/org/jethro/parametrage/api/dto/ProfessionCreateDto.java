package org.jethro.parametrage.api.dto;

import java.util.Objects;


public class ProfessionCreateDto extends BaseEntityDTO {

    private String libelle;
    private String description;
    private String categorieProfessionUuid;

    public ProfessionCreateDto() {
    }

    public ProfessionCreateDto(String libelle, String description, String categorieProfessionUuid) {
        this.libelle = libelle;
        this.description = description;
        this.categorieProfessionUuid = categorieProfessionUuid;
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

    public String getCategorieProfessionUuid() {
        return categorieProfessionUuid;
    }

    public void setCategorieProfessionUuid(String categorieProfessionUuid) {
        this.categorieProfessionUuid = categorieProfessionUuid;
    }

    @Override
    public String toString() {
        return "ProfessionCreateDto{" +
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
        ProfessionCreateDto that = (ProfessionCreateDto) o;
        return Objects.equals(libelle, that.libelle) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }
}
