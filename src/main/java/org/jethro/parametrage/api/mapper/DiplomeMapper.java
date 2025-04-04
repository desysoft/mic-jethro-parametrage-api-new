package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.DiplomeDto;
import org.jethro.parametrage.api.entities.Diplome;
import org.jethro.parametrage.api.entities.views.VDiplome;

@Dependent
public class DiplomeMapper implements BaseMapper<Diplome, DiplomeDto>, BaseViewMapper<VDiplome, DiplomeDto>{


  @Override
  public DiplomeDto toDto(Diplome neighborhood) {
    return null;
  }

  @Override
  public Diplome toEntity(DiplomeDto neighborhoodDto) {
    return null;
  }


  @Override
  public DiplomeDto dtoByView(VDiplome vDiplome) {
    DiplomeDto dto = new DiplomeDto();
    dto.setUuid(vDiplome.uuid);
    dto.setCode(vDiplome.code);
    dto.setLibelle(vDiplome.libelle);
    dto.setNombreHomme(vDiplome.nombreHomme);
    dto.setNombreFemme(vDiplome.nombreFemme);
    dto.setTotalPersonne(vDiplome.totalPersons);
    return dto;
  }



}
