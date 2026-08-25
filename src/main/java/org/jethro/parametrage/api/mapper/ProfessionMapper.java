package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dto.ProfessionCreateDto;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.dto.ProfessionUpdateDto;
import org.jethro.parametrage.api.entities.Profession;
import org.jethro.parametrage.api.entities.views.VProfession;
import org.jethro.parametrage.api.services.CategorieProfessionService;

@Dependent
public class ProfessionMapper implements BaseMapper<Profession, ProfessionDto>, BaseViewMapper<VProfession, ProfessionDto>{

  @Inject
  CategorieProfessionService categorieProfessionService;

  @Override
  public ProfessionDto toDto(Profession neighborhood) {
    return null;
  }

  @Override
  public Profession toEntity(ProfessionDto neighborhoodDto) {
    return null;
  }

  public Profession createDtoToEntity(ProfessionCreateDto professionCreateDto) {
    Profession profession = new Profession();
    profession.code = professionCreateDto.getCode();
    profession.libelle = professionCreateDto.getLibelle();
    profession.description = professionCreateDto.getDescription();
    if (professionCreateDto.getCategorieProfessionUuid() != null && !professionCreateDto.getCategorieProfessionUuid().isEmpty()) {
      profession.categorieProfession = categorieProfessionService.touverParId(professionCreateDto.getCategorieProfessionUuid());
    }
    return profession;
  }

  public Profession updateDtoToEntity(ProfessionUpdateDto professionUpdateDto) {
    Profession profession = new Profession();
    profession.code = professionUpdateDto.getCode();
    profession.libelle = professionUpdateDto.getLibelle();
    profession.description = professionUpdateDto.getDescription();
    if (professionUpdateDto.getCategorieProfessionUuid() != null && !professionUpdateDto.getCategorieProfessionUuid().isEmpty()) {
      profession.categorieProfession = categorieProfessionService.touverParId(professionUpdateDto.getCategorieProfessionUuid());
    }
    return profession;
  }


  @Override
  public ProfessionDto dtoByView(VProfession vProfession) {
    ProfessionDto dto = new ProfessionDto();
    dto.setUuid(vProfession.uuid);
    dto.setCode(vProfession.code);
    dto.setLibelle(vProfession.libelle);
    dto.setNombreHomme(vProfession.nombreHomme);
    dto.setNombreFemme(vProfession.nombreFemme);
    dto.setTotalPersons(vProfession.totalPersons);
    return dto;
  }



}