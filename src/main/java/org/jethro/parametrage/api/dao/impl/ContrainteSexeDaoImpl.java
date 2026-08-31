package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.ContrainteSexeDao;
import org.jethro.parametrage.api.entities.ContrainteSexe;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class ContrainteSexeDaoImpl extends CommonDao<ContrainteSexe> implements
    ContrainteSexeDao {

    public ContrainteSexe save(ContrainteSexe contrainteSexe){
        try {
            LOG.info("save");
            if(contrainteSexe.code == null) {
                contrainteSexe.code = ToolString.getComplexId(ParametersConfig.CONTRAINTE_SEXE_CODE_PREFIXE);
            }
            if(this.isExistCode(contrainteSexe.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(contrainteSexe);
            return contrainteSexe;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ContrainteSexe update(String uuid, ContrainteSexe contrainteSexeForUpdate){
        try {
            ContrainteSexe contrainteSexeFind = this.findByIdCustom(uuid);
            if(contrainteSexeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(contrainteSexeForUpdate.code!=null) contrainteSexeFind.code = contrainteSexeForUpdate.code;
            if(contrainteSexeForUpdate.libelle!=null) contrainteSexeFind.libelle = contrainteSexeForUpdate.libelle;
            if(contrainteSexeForUpdate.description!=null) contrainteSexeFind.description = contrainteSexeForUpdate.description;
            this.persist(contrainteSexeFind);
            return contrainteSexeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        ContrainteSexe contrainteSexe = this.findByIdCustom(uuid);
        if(contrainteSexe==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        contrainteSexe.status = ParametersConfig.status_delete;
        this.persist(contrainteSexe);
        return this.findByIdCustom(uuid)==null;
    }
}
