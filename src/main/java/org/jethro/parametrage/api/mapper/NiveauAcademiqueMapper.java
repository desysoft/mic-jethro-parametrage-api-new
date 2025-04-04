package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.NiveauAcademiqueDto;
import org.jethro.parametrage.api.entities.NiveauAcademique;
import org.jethro.parametrage.api.entities.views.VNiveauAcademique;

@Dependent
public class NiveauAcademiqueMapper implements BaseMapper<NiveauAcademique, NiveauAcademiqueDto>, BaseViewMapper<VNiveauAcademique, NiveauAcademiqueDto>{


  @Override
  public NiveauAcademiqueDto toDto(NiveauAcademique neighborhood) {
    return null;
  }

  @Override
  public NiveauAcademique toEntity(NiveauAcademiqueDto neighborhoodDto) {
    return null;
  }


  @Override
  public NiveauAcademiqueDto dtoByView(VNiveauAcademique vNiveauAcademique) {
    NiveauAcademiqueDto dto = new NiveauAcademiqueDto();
    dto.setUuid(vNiveauAcademique.uuid);
    dto.setCode(vNiveauAcademique.code);
    dto.setLibelle(vNiveauAcademique.libelle);
    dto.setNombreHomme(vNiveauAcademique.nombreHomme);
    dto.setNombreFemme(vNiveauAcademique.nombreFemme);
    dto.setTotalPersonne(vNiveauAcademique.totalPersons);
    return dto;
  }



}
