package org.jethro.parametrage.api.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jethro.parametrage.api.entities.views.VQuartier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Municipality extends BaseEntityDTO {

    private String libelle;
    private String description;
    private Integer nombreFemme;
    private Integer nombreHomme;
    private Integer totalPersonne;


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

}
