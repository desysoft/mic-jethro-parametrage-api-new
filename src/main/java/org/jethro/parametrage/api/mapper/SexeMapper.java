package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.SexeDto;
import org.jethro.parametrage.api.entities.Sexe;
import org.jethro.parametrage.api.entities.views.VSexe;

@Dependent
public class SexeMapper implements BaseMapper<Sexe, SexeDto>, BaseViewMapper<VSexe, SexeDto>{


  @Override
  public SexeDto toDto(Sexe neighborhood) {
    return null;
  }

  @Override
  public Sexe toEntity(SexeDto neighborhoodDto) {
    return null;
  }


  @Override
  public SexeDto dtoByView(VSexe vSexe) {
    SexeDto dto = new SexeDto();
    dto.setUuid(vSexe.uuid);
    dto.setCode(vSexe.code);
    dto.setLibelle(vSexe.libelle);
    dto.setTotalPersonne(vSexe.totalPersons);
    return dto;
  }



}
