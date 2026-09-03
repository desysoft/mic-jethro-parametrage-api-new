package org.jethro.parametrage.api.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProfessionUpdateDto extends BaseEntityDTO {

    private String libelle;
    private String description;
    private String categorieProfessionUuid;

    /**
     * categorieProfessionUuid null signifie "champ non fourni, ne pas toucher" (mise à jour
     * partielle, cf. ProfessionDaoImpl#update) : impossible avec un simple String de distinguer
     * "non fourni" de "explicitement vidé", donc un client ne pouvait jamais détacher une
     * profession de sa catégorie via cet endpoint (le champ restait figé sur son ancienne
     * valeur). Ce flag, à true, demande explicitement ce détachement.
     */
    private boolean detacherCategorieProfession;
}
