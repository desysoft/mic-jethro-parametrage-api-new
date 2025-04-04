package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.FiliereDto;
import org.jethro.parametrage.api.entities.Filiere;
import org.jethro.parametrage.api.entities.views.VFiliere;

@Dependent
public class FiliereMapper implements BaseMapper<Filiere, FiliereDto>, BaseViewMapper<VFiliere, FiliereDto>{


  @Override
  public FiliereDto toDto(Filiere neighborhood) {
    return null;
  }

  @Override
  public Filiere toEntity(FiliereDto neighborhoodDto) {
    return null;
  }


  @Override
  public FiliereDto dtoByView(VFiliere vFiliere) {
    FiliereDto dto = new FiliereDto();
    dto.setUuid(vFiliere.uuid);
    dto.setCode(vFiliere.code);
    dto.setLibelle(vFiliere.libelle);
    dto.setNombreHomme(vFiliere.nombreHomme);
    dto.setNombreFemme(vFiliere.nombreFemme);
    dto.setTotalPersonne(vFiliere.totalPersons);
    return dto;
  }



}