package org.jethro.parametrage.api.dto;

import java.util.Objects;


public class NeighborhoodCreateDto extends BaseEntityDTO {

    private String libelle;
    private String description;
    private String municipalityUuid;

    public NeighborhoodCreateDto(String libelle, String description,String municipalityUuid) {
        this.libelle = libelle;
        this.description = description;
        this.municipalityUuid = municipalityUuid;
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

    public String getMunicipalityUuid() {
        return municipalityUuid;
    }

    public void setMunicipalityUuid(String municipalityUuid) {
        this.municipalityUuid = municipalityUuid;
    }

    @Override
    public String toString() {
        return "NeighborhoodDto{" +
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
        NeighborhoodCreateDto neighborhood = (NeighborhoodCreateDto) o;
        return Objects.equals(libelle, neighborhood.libelle) && Objects.equals(description, neighborhood.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }
}
