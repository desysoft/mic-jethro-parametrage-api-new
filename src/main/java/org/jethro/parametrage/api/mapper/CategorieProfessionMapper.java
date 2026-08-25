package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.CategorieProfessionDto;
import org.jethro.parametrage.api.entities.views.VCategorieProfession;

@Dependent
public class CategorieProfessionMapper implements BaseViewMapper<VCategorieProfession, CategorieProfessionDto> {

  @Override
  public CategorieProfessionDto dtoByView(VCategorieProfession vCategorieProfession) {
    CategorieProfessionDto dto = new CategorieProfessionDto();
    dto.setUuid(vCategorieProfession.uuid);
    dto.setCode(vCategorieProfession.code);
    dto.setLibelle(vCategorieProfession.libelle);
    dto.setDescription(vCategorieProfession.description);
    dto.setNombreHomme(vCategorieProfession.nombreHomme);
    dto.setNombreFemme(vCategorieProfession.nombreFemme);
    dto.setTotalPersons(vCategorieProfession.totalPersons);
    return dto;
  }
}
