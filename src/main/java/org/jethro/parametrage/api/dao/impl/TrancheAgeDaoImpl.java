package org.jethro.parametrage.api.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.TrancheAgeDao;
import org.jethro.parametrage.api.entities.TrancheAge;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class TrancheAgeDaoImpl extends CommonDao<TrancheAge> implements
    TrancheAgeDao {


    public TrancheAge save(TrancheAge trancheAge){
        try {
            LOG.info("save");
            if(trancheAge.code == null) {
                trancheAge.code = ToolString.getComplexId(ParametersConfig.SLICE_AGE_CODE_PREFIXE);
            }
            if(this.isExistCode(trancheAge.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(trancheAge);
            return trancheAge;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TrancheAge update(String uuid, TrancheAge trancheAgeForUpdate){
        try {
            TrancheAge trancheAgeFind = this.findByIdCustom(uuid);
            if(trancheAgeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(trancheAgeForUpdate.code!=null) trancheAgeFind.code = trancheAgeForUpdate.code;
            if(trancheAgeForUpdate.libelle!=null) trancheAgeFind.libelle = trancheAgeForUpdate.libelle;
            if(trancheAgeForUpdate.description!=null) trancheAgeFind.description = trancheAgeForUpdate.description;
            this.persist(trancheAgeFind);
            return trancheAgeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        TrancheAge trancheAge = this.findByIdCustom(uuid);
        if(trancheAge==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        trancheAge.status = ParametersConfig.status_delete;
        this.persist(trancheAge);
        return this.findByIdCustom(uuid)==null;
    }
}
