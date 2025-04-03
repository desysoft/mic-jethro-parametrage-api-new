package org.jethro.parametrage.api.services.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.NeighborhoodDao;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;
import org.jethro.parametrage.api.services.NeighborhoodService;


@ApplicationScoped
public class NeighborhoodServiceImpl implements NeighborhoodService {

     @Inject
     NeighborhoodDao neighborhoodDao;

     @Override
     public List<Neighborhood> obtenirListe(int pageIndex, int pageSize) {
          if(pageSize==0){
               return neighborhoodDao.getList();
          }else{
               return neighborhoodDao.getList(pageIndex,pageSize);
          }
     }

     @Override
     public List<Neighborhood> rechercher(String searchValue, int pageindex, int pageSize) {
          return neighborhoodDao.getList(searchValue, pageindex,pageSize);
     }

     @Override
     public Neighborhood touverParId(String id) {
          return neighborhoodDao.findByIdCustom(id);
     }

     @Override
     public Neighborhood touverParCode(String code) {
          return neighborhoodDao.findByCode(code);
     }

     @Override
     public Neighborhood ajouter(Neighborhood neighborhood) {
          return neighborhoodDao.save(neighborhood);
     }

     @Override
     public Neighborhood modifer(String id, Neighborhood neighborhood) {
          return neighborhoodDao.update(id, neighborhood);
     }

     @Override
     public Boolean supprimer(String id) {
          return neighborhoodDao.delete(id);
     }


     public List<Neighborhood> obtenirListeDesQuartiersParCommune(String idCommune, int pageIndex, int pageSize){
          return neighborhoodDao.getByCommune(idCommune,pageIndex,pageSize);
     }


     @Override
     public Neighborhood ajouterParNeighborhoodCreateDto(
         NeighborhoodCreateDto neighborhoodCreateDto) {
          return neighborhoodDao.saveByNeighborhoodCreateDTO(neighborhoodCreateDto);
     }

     @Override
     public Neighborhood modifierParNeighborhoodUpdateDto(
         NeighborhoodUpdateDto neighborhoodUpdateDto) {
          return neighborhoodDao.updateByNeighborhoodUpdateDto(neighborhoodUpdateDto);
     }
}
