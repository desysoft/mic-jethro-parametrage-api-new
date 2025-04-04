package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.TrancheAgeDto;
import org.jethro.parametrage.api.entities.TrancheAge;
import org.jethro.parametrage.api.entities.views.VTrancheAge;

@Dependent
public class TrancheAgeMapper implements BaseMapper<TrancheAge, TrancheAgeDto>, BaseViewMapper<VTrancheAge, TrancheAgeDto>{


  @Override
  public TrancheAgeDto toDto(TrancheAge neighborhood) {
    return null;
  }

  @Override
  public TrancheAge toEntity(TrancheAgeDto neighborhoodDto) {
    return null;
  }


  @Override
  public TrancheAgeDto dtoByView(VTrancheAge vTrancheAge) {
    TrancheAgeDto dto = new TrancheAgeDto();
    dto.setUuid(vTrancheAge.uuid);
    dto.setCode(vTrancheAge.code);
    dto.setLibelle(vTrancheAge.libelle);
    dto.setNombreHomme(vTrancheAge.nombreHomme);
    dto.setNombreFemme(vTrancheAge.nombreFemme);
    dto.setTotalPersonne(vTrancheAge.totalPersons);
    return dto;
  }



}
