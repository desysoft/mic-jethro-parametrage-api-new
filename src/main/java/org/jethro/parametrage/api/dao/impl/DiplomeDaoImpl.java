package org.jethro.parametrage.api.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.DiplomeDao;
import org.jethro.parametrage.api.entities.Diplome;
import org.jethro.parametrage.api.tools.ParametersConfig;

@ApplicationScoped
public class DiplomeDaoImpl extends CommonDao<Diplome> implements
    DiplomeDao {


    public Diplome save(Diplome diplome){
        try {
            LOG.info("save");
            if(this.isExistCode(diplome.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(diplome);
            return diplome;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Diplome update(String uuid, Diplome diplomeForUpdate){
        try {
            Diplome diplomeFind = this.findByIdCustom(uuid);
            if(diplomeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(diplomeForUpdate.code!=null) diplomeFind.code = diplomeForUpdate.code;
            if(diplomeForUpdate.libelle!=null) diplomeFind.libelle = diplomeForUpdate.libelle;
            if(diplomeForUpdate.description!=null) diplomeFind.description = diplomeForUpdate.description;
            this.persist(diplomeFind);
            return diplomeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        Diplome diplome = this.findByIdCustom(uuid);
        if(diplome==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        diplome.status = ParametersConfig.status_delete;
        this.persist(diplome);
        return this.findByIdCustom(uuid)==null;
    }
}
