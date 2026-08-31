package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.SensDao;
import org.jethro.parametrage.api.entities.Sens;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class SensDaoImpl extends CommonDao<Sens> implements
    SensDao {

    public Sens save(Sens sens){
        try {
            LOG.info("save");
            if(sens.code == null) {
                sens.code = ToolString.getComplexId(ParametersConfig.SENS_CODE_PREFIXE);
            }
            if(this.isExistCode(sens.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(sens);
            return sens;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Sens update(String uuid, Sens sensForUpdate){
        try {
            Sens sensFind = this.findByIdCustom(uuid);
            if(sensFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(sensForUpdate.code!=null) sensFind.code = sensForUpdate.code;
            if(sensForUpdate.libelle!=null) sensFind.libelle = sensForUpdate.libelle;
            if(sensForUpdate.description!=null) sensFind.description = sensForUpdate.description;
            this.persist(sensFind);
            return sensFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Sens sens = this.findByIdCustom(uuid);
        if(sens==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        sens.status = ParametersConfig.status_delete;
        this.persist(sens);
        return this.findByIdCustom(uuid)==null;
    }
}
