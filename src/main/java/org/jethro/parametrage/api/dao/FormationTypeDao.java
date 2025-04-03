package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.entities.FormationType;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormationTypeDao extends CommonDao<FormationType> {


    public FormationType save(FormationType formationType){
        try {
            LOG.info("save");
            if(this.isExistCode(formationType.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(formationType);
            return formationType;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public FormationType update(String uuid, FormationType formationTypeForUpdate){
        try {
            FormationType formationTypeFind = this.findByIdCustom(uuid);
            if(formationTypeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(formationTypeForUpdate.code!=null) formationTypeFind.code = formationTypeForUpdate.code;
            if(formationTypeForUpdate.libelle!=null) formationTypeFind.libelle = formationTypeForUpdate.libelle;
            if(formationTypeForUpdate.description!=null) formationTypeFind.description = formationTypeForUpdate.description;
            this.persist(formationTypeFind);
            return formationTypeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        FormationType formationType = this.findByIdCustom(uuid);
        if(formationType==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        formationType.status = ParametersConfig.status_delete;
        this.persist(formationType);
        return this.findByIdCustom(uuid)==null;
    }
}
