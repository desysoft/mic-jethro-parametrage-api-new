package org.jethro.parametrage.api.dao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jethro.parametrage.api.entities.Formation;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class FormationDao extends CommonDao<Formation> {

    public List<Formation> getByFormationType(String idFormationType, int pageIndex, int pageSize){
        Map<String, Object> params = new HashMap<>();
        idFormationType = checkAndGetForSearchValue(idFormationType);
        params.put("idFormationType", idFormationType);
        PanacheQuery<Formation> panacheQuery =  find("formationType.uuid LIKE ?1 and status = ?2",idFormationType, ParametersConfig.status_enable);
        if(pageSize==0){
            return panacheQuery.list();
        }else {
            return panacheQuery.page(pageIndex,pageSize).list();
        }
    }

    public Formation save(Formation formation){
        try {
            LOG.info("save");
            if(this.isExistCode(formation.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(formation);
            return formation;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Formation update(String uuid, Formation formationForUpdate){
        try {
            Formation formationFind = this.findByIdCustom(uuid);
            if(formationFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(formationForUpdate.code!=null) formationFind.code = formationForUpdate.code;
            if(formationForUpdate.libelle!=null) formationFind.libelle = formationForUpdate.libelle;
            if(formationForUpdate.description!=null) formationFind.description = formationForUpdate.description;
            if(formationForUpdate.formationType!=null) formationFind.formationType = formationForUpdate.formationType;
            this.persist(formationFind);
            return formationFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Formation formation = this.findByIdCustom(uuid);
        if(formation==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        formation.status = ParametersConfig.status_delete;
        this.persist(formation);
        return this.findByIdCustom(uuid)==null;
    }
}
