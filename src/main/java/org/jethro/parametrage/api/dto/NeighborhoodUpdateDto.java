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
public class NeighborhoodUpdateDto extends BaseEntityDTO {

    private String libelle;
    private String description;
    private String municipalityUuid;
}
