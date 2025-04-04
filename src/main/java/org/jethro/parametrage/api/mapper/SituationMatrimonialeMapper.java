package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.SituationMatrimonialeDto;
import org.jethro.parametrage.api.entities.SituationMatrimoniale;
import org.jethro.parametrage.api.entities.views.VSituationMatrimoniale;

@Dependent
public class SituationMatrimonialeMapper implements BaseMapper<SituationMatrimoniale, SituationMatrimonialeDto>, BaseViewMapper<VSituationMatrimoniale, SituationMatrimonialeDto>{


  @Override
  public SituationMatrimonialeDto toDto(SituationMatrimoniale neighborhood) {
    return null;
  }

  @Override
  public SituationMatrimoniale toEntity(SituationMatrimonialeDto neighborhoodDto) {
    return null;
  }


  @Override
  public SituationMatrimonialeDto dtoByView(VSituationMatrimoniale vSituationMatrimoniale) {
    SituationMatrimonialeDto dto = new SituationMatrimonialeDto();
    dto.setUuid(vSituationMatrimoniale.uuid);
    dto.setCode(vSituationMatrimoniale.code);
    dto.setLibelle(vSituationMatrimoniale.libelle);
    dto.setNombreHomme(vSituationMatrimoniale.nombreHomme);
    dto.setNombreFemme(vSituationMatrimoniale.nombreFemme);
    dto.setTotalPersonne(vSituationMatrimoniale.totalPersons);
    return dto;
  }



}
