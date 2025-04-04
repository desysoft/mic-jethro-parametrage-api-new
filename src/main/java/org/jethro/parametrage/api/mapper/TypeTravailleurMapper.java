package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.TypeTravailleurDto;
import org.jethro.parametrage.api.entities.TypeTravailleur;
import org.jethro.parametrage.api.entities.views.VTypeTravailleur;

@Dependent
public class TypeTravailleurMapper implements BaseMapper<TypeTravailleur, TypeTravailleurDto>, BaseViewMapper<VTypeTravailleur, TypeTravailleurDto>{


  @Override
  public TypeTravailleurDto toDto(TypeTravailleur neighborhood) {
    return null;
  }

  @Override
  public TypeTravailleur toEntity(TypeTravailleurDto neighborhoodDto) {
    return null;
  }


  @Override
  public TypeTravailleurDto dtoByView(VTypeTravailleur vTypeTravailleur) {
    TypeTravailleurDto dto = new TypeTravailleurDto();
    dto.setUuid(vTypeTravailleur.uuid);
    dto.setCode(vTypeTravailleur.code);
    dto.setLibelle(vTypeTravailleur.libelle);
    dto.setNombreHomme(vTypeTravailleur.nombreHomme);
    dto.setNombreFemme(vTypeTravailleur.nombreFemme);
    dto.setTotalPersonne(vTypeTravailleur.totalPersons);
    return dto;
  }



}
