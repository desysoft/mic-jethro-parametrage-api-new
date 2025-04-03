package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;

import java.util.List;

public interface NeighborhoodDao extends CRUDCommon<Neighborhood> {
    List<Neighborhood> getByCommune(String idCommune, int pageIndex, int pageSize);
    Neighborhood saveByNeighborhoodCreateDTO(NeighborhoodCreateDto neighborhoodCreateDto);

    Neighborhood updateByNeighborhoodUpdateDto(NeighborhoodUpdateDto neighborhoodUpdateDTO);
}
