package org.jethro.parametrage.api.dao.impl;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.NeighborhoodDao;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;
import org.jethro.parametrage.api.exceptions.CodeExistException;
import org.jethro.parametrage.api.exceptions.ObjectNotFoundException;
import org.jethro.parametrage.api.mapper.NeighborhoodMapper;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class NeighborhoodDaoImpl extends CommonDao<Neighborhood> implements NeighborhoodDao {

    @Inject
    NeighborhoodMapper neighborhoodMapper;

    public List<Neighborhood> getByCommune(String idCommune, int pageIndex, int pageSize){
        Map<String, Object> params = new HashMap<>();
        idCommune = checkAndGetForSearchValue(idCommune);
        params.put("idCommune", idCommune);
        PanacheQuery<Neighborhood> panacheQuery =  find("commune.uuid LIKE ?1 and status = ?2",idCommune, ParametersConfig.status_enable);
        if(pageSize==0){
            return panacheQuery.list();
        }else {
            return panacheQuery.page(pageIndex,pageSize).list();
        }
    }

    @Override
    public Neighborhood saveByNeighborhoodCreateDTO(NeighborhoodCreateDto neighborhoodCreateDto) {
        Neighborhood neighborhood = neighborhoodMapper.createDtoToEntity(neighborhoodCreateDto);
        return this.save(neighborhood);
    }

    @Override
    public Neighborhood updateByNeighborhoodUpdateDto(NeighborhoodUpdateDto neighborhoodUpdateDTO) {
        Neighborhood neighborhood = neighborhoodMapper.updateDtoToEntity(neighborhoodUpdateDTO);
        return this.update(neighborhoodUpdateDTO.getUuid(), neighborhood);
    }

    public String getName(){
        return "kcjdcg";
    }

    public Neighborhood save(Neighborhood neighborhood){
        try {
            LOG.info("save");
            if(neighborhood.code == null || neighborhood.code.isEmpty()) {
                neighborhood.code = ToolString.getComplexId();
            }
            if(this.isExistCode(neighborhood.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                throw new CodeExistException(this.getDetailMessage());
            }
            this.persist(neighborhood);
            return neighborhood;
        }catch (Exception e){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            throw new RuntimeException(e);
        }
    }

    public Neighborhood update(String uuid, Neighborhood neighborhoodForUpdate){
        try {
            Neighborhood neighborhoodFind = this.findByIdCustom(uuid);
            if(neighborhoodFind ==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                throw new ObjectNotFoundException(this.getDetailMessage());
            }

            if(neighborhoodForUpdate.code!=null) neighborhoodFind.code = neighborhoodForUpdate.code;
            if(neighborhoodForUpdate.libelle!=null) neighborhoodFind.libelle = neighborhoodForUpdate.libelle;
            if(neighborhoodForUpdate.description!=null) neighborhoodFind.description = neighborhoodForUpdate.description;
            if(neighborhoodForUpdate.commune!=null) neighborhoodFind.commune = neighborhoodForUpdate.commune;
            System.out.println("update +++ neighborhoodForUpdate.commune === "+neighborhoodForUpdate.commune);
            this.persist(neighborhoodFind);
            return neighborhoodFind;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String uuid){
        try {
            Neighborhood neighborhood = this.findByIdCustom(uuid);
            if(neighborhood ==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return false;
            }
            neighborhood.delete();
            return this.findByIdCustom(uuid)==null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
