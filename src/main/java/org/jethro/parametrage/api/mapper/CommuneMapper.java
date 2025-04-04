package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.Municipality;
import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.entities.views.VCommune;

@Dependent
public class CommuneMapper  implements BaseMapper<Commune, Municipality>, BaseViewMapper<VCommune, Municipality>{
  @Override
  public Municipality toDto(Commune commune) {
    Municipality municipality = new Municipality();
    municipality.setUuid(commune.uuid);
    municipality.setCode(commune.code);
    municipality.setLibelle(commune.libelle);
    return municipality;
  }

  @Override
  public Commune toEntity(Municipality municipality) {
    Commune commune = new Commune();
    commune.code = municipality.getCode();
    commune.libelle = municipality.getLibelle();
    return commune;
  }

  @Override
  public Municipality dtoByView(VCommune vMunicipality) {
    Municipality dto = new Municipality();
    dto.setUuid(vMunicipality.uuid);
    dto.setCode(vMunicipality.code);
    dto.setLibelle(vMunicipality.libelle);
    dto.setNombreHomme(vMunicipality.nombreHomme);
    dto.setNombreFemme(vMunicipality.nombreFemme);
    dto.setTotalPersonne(vMunicipality.totalPersons);
    return dto;
  }

}
