package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.Profession;
import org.jethro.parametrage.api.entities.views.VProfession;

@Dependent
public class ProfessionMapper implements BaseMapper<Profession, ProfessionDto>, BaseViewMapper<VProfession, ProfessionDto>{


  @Override
  public ProfessionDto toDto(Profession neighborhood) {
    return null;
  }

  @Override
  public Profession toEntity(ProfessionDto neighborhoodDto) {
    return null;
  }


  @Override
  public ProfessionDto dtoByView(VProfession vProfession) {
    ProfessionDto dto = new ProfessionDto();
    dto.setUuid(vProfession.uuid);
    dto.setCode(vProfession.code);
    dto.setLibelle(vProfession.libelle);
    dto.setNombreHomme(vProfession.nombreHomme);
    dto.setNombreFemme(vProfession.nombreFemme);
    dto.setTotalPersonne(vProfession.totalPersons);
    return dto;
  }



}