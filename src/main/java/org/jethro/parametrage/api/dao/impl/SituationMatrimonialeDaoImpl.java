package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.SituationMatrimonialeDao;
import org.jethro.parametrage.api.entities.SituationMatrimoniale;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class SituationMatrimonialeDaoImpl extends CommonDao<SituationMatrimoniale> implements
    SituationMatrimonialeDao {

    public SituationMatrimoniale save(SituationMatrimoniale situationMatrimoniale){
        try {
            LOG.info("save");
            if(situationMatrimoniale.code == null) {
                situationMatrimoniale.code = ToolString.getComplexId(ParametersConfig.MARITAL_STATUS_CODE_PREFIXE);
            }
            if(this.isExistCode(situationMatrimoniale.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(situationMatrimoniale);
            return situationMatrimoniale;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public SituationMatrimoniale update(String uuid, SituationMatrimoniale situationMatrimonialeForUpdate){
        try {
            SituationMatrimoniale situationMatrimonialeFind = this.findByIdCustom(uuid);
            if(situationMatrimonialeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(situationMatrimonialeForUpdate.code!=null) situationMatrimonialeFind.code = situationMatrimonialeForUpdate.code;
            if(situationMatrimonialeForUpdate.libelle!=null) situationMatrimonialeFind.libelle = situationMatrimonialeForUpdate.libelle;
            if(situationMatrimonialeForUpdate.description!=null) situationMatrimonialeFind.description = situationMatrimonialeForUpdate.description;
            this.persist(situationMatrimonialeFind);
            return situationMatrimonialeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        SituationMatrimoniale situationMatrimoniale = this.findByIdCustom(uuid);
        if(situationMatrimoniale==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        situationMatrimoniale.status = ParametersConfig.status_delete;
        this.persist(situationMatrimoniale);
        return this.findByIdCustom(uuid)==null;
    }
}
