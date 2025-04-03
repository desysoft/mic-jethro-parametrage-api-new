package org.jethro.parametrage.api.services;

import jakarta.validation.constraints.NotNull;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;

import java.util.List;


public interface NeighborhoodService extends BasicCommonService<Neighborhood> {

  List<Neighborhood> obtenirListeDesQuartiersParCommune(String idCommune, int pageIndex,
                                                        int pageSize);

  Neighborhood ajouterParNeighborhoodCreateDto(@NotNull(message = "Objet nul")
                                               NeighborhoodCreateDto neighborhoodCreateDto);

  Neighborhood modifierParNeighborhoodUpdateDto(@NotNull(message = "Objet nul")
                                                NeighborhoodUpdateDto neighborhoodUpdateDto);
}
