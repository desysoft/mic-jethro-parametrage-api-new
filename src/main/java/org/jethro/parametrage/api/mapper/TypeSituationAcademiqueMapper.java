package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.TypeSituationAcademiqueDto;
import org.jethro.parametrage.api.entities.TypeSituationAcademique;
import org.jethro.parametrage.api.entities.views.VTypeSituationAcademique;

@Dependent
public class TypeSituationAcademiqueMapper implements BaseMapper<TypeSituationAcademique, TypeSituationAcademiqueDto>, BaseViewMapper<VTypeSituationAcademique, TypeSituationAcademiqueDto>{


  @Override
  public TypeSituationAcademiqueDto toDto(TypeSituationAcademique neighborhood) {
    return null;
  }

  @Override
  public TypeSituationAcademique toEntity(TypeSituationAcademiqueDto neighborhoodDto) {
    return null;
  }


  @Override
  public TypeSituationAcademiqueDto dtoByView(VTypeSituationAcademique vTypeSituationAcademique) {
    TypeSituationAcademiqueDto dto = new TypeSituationAcademiqueDto();
    dto.setUuid(vTypeSituationAcademique.uuid);
    dto.setCode(vTypeSituationAcademique.code);
    dto.setLibelle(vTypeSituationAcademique.libelle);
    dto.setNombreHomme(vTypeSituationAcademique.nombreHomme);
    dto.setNombreFemme(vTypeSituationAcademique.nombreFemme);
    dto.setTotalPersonne(vTypeSituationAcademique.totalPersons);
    return dto;
  }



}
