package org.jethro.parametrage.api.mapper;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.List;
import org.jethro.parametrage.api.dto.Municipality;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;
import org.jethro.parametrage.api.entities.views.VQuartier;
import org.jethro.parametrage.api.services.CommuneService;

@Dependent
public class NeighborhoodMapper implements BaseMapper<Neighborhood, NeighborhoodDto>, BaseViewMapper<VQuartier, NeighborhoodDto>{

  @Inject
  CommuneService communeService;

  @Inject
  CommuneMapper communeMapper;

  @Override
  public NeighborhoodDto toDto(Neighborhood neighborhood) {
    NeighborhoodDto neighborhoodDto = new NeighborhoodDto();
    neighborhoodDto.setUuid(neighborhood.uuid);
    neighborhoodDto.setCode(neighborhood.code);
    neighborhoodDto.setLibelle(neighborhood.libelle);
    if(neighborhood.commune != null){
      System.out.println(neighborhood.commune);
      neighborhoodDto.setMunicipality(communeMapper.toDto(neighborhood.commune));
    }
    return neighborhoodDto;
  }

  @Override
  public Neighborhood toEntity(NeighborhoodDto neighborhoodDto) {
    return null;
  }

  public Neighborhood createDtoToEntity(NeighborhoodCreateDto neighborhoodCreateDto) {
    Neighborhood neighborhood = new Neighborhood();
    neighborhood.code = neighborhoodCreateDto.getCode();
    neighborhood.libelle = neighborhoodCreateDto.getLibelle();
    neighborhood.commune = communeService.touverParId(neighborhoodCreateDto.getMunicipalityUuid());
    return neighborhood;
  }

  public Neighborhood updateDtoToEntity(NeighborhoodUpdateDto neighborhoodUpdateDto) {
    Neighborhood neighborhood = new Neighborhood();
    neighborhood.code = neighborhoodUpdateDto.getCode();
    neighborhood.libelle = neighborhoodUpdateDto.getLibelle();
    neighborhood.commune = communeService.touverParId(neighborhoodUpdateDto.getMunicipalityUuid());
    return neighborhood;
  }


  @Override
  public NeighborhoodDto dtoByView(VQuartier vQuartier) {
    NeighborhoodDto neighborhoodDto = new NeighborhoodDto();
    neighborhoodDto.setUuid(vQuartier.uuid);
    neighborhoodDto.setCode(vQuartier.code);
    neighborhoodDto.setLibelle(vQuartier.libelle);
    neighborhoodDto.setNombreHomme(vQuartier.nombreHomme);
    neighborhoodDto.setNombreFemme(vQuartier.nombreFemme);
    neighborhoodDto.setTotalPersonne(vQuartier.totalPersons);
    neighborhoodDto.setMunicipality(new Municipality(vQuartier));
    return neighborhoodDto;
  }



}