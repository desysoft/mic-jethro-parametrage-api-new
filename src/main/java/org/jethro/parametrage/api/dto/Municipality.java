package org.jethro.parametrage.api.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.jethro.parametrage.api.entities.views.VQuartier;

@Getter
@Setter
public class Municipality extends BaseEntityDTO {

    private String libelle;
    private String description;

    public Municipality() {
    }

    public Municipality(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }
    public Municipality(String uuid, String libelle, String description) {
        this.uuid = uuid;
        this.libelle = libelle;
        this.description = description;
    }

    public Municipality(VQuartier vQuartier) {
        this.uuid = (vQuartier!=null)?vQuartier.idCommune:null;
        this.code = (vQuartier!=null)?vQuartier.codeCommune:null;
        this.libelle = (vQuartier!=null)?vQuartier.libelleCommune:null;
    }

    @Override
    public String toString() {
        return "Quartier{" +
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
        Municipality quartier = (Municipality) o;
        return Objects.equals(libelle, quartier.libelle) && Objects.equals(description, quartier.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle, description);
    }
}
