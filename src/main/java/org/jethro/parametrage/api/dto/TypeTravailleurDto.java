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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TypeTravailleurDto extends BaseEntityDTO {

    private String libelle;
    private String description;
    private Integer nombreFemme;
    private Integer nombreHomme;
    private Integer totalPersonne;

    public TypeTravailleurDto(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }
}

