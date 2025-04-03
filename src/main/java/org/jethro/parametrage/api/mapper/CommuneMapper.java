package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import org.jethro.parametrage.api.dto.Municipality;
import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.entities.views.VQuartier;

@Dependent
public class CommuneMapper  implements BaseMapper<Commune, Municipality>{
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

}
