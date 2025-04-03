package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.entities.Sexe;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SexeDao extends CommonDao<Sexe> {

    public Sexe save(Sexe sexe){
        try {
            LOG.info("save");
            if(this.isExistCode(sexe.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(sexe);
            return sexe;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Sexe update(String uuid, Sexe sexeForUpdate){
        try {
            Sexe sexeFind = this.findByIdCustom(uuid);
            if(sexeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(sexeForUpdate.code!=null) sexeFind.code = sexeForUpdate.code;
            if(sexeForUpdate.libelle!=null) sexeFind.libelle = sexeForUpdate.libelle;
            if(sexeForUpdate.description!=null) sexeFind.description = sexeForUpdate.description;
            this.persist(sexeFind);
            return sexeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Sexe sexe = this.findByIdCustom(uuid);
        if(sexe==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        sexe.status = ParametersConfig.status_delete;
        this.persist(sexe);
        return this.findByIdCustom(uuid)==null;
    }
}
