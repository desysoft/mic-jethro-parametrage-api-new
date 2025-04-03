package org.jethro.parametrage.api.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.NiveauAcademiqueDao;
import org.jethro.parametrage.api.entities.NiveauAcademique;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class NiveauAcademiqueDaoImpl extends CommonDao<NiveauAcademique> implements
    NiveauAcademiqueDao {


    public NiveauAcademique save(NiveauAcademique niveauAcademique){
        try {
            LOG.info("save");
            if(niveauAcademique.code == null) {
                niveauAcademique.code = ToolString.getComplexId();
            }
            if(this.isExistCode(niveauAcademique.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(niveauAcademique);
            return niveauAcademique;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public NiveauAcademique update(String uuid, NiveauAcademique niveauAcademiqueForUpdate){
        try {
            NiveauAcademique niveauAcademiqueFind = this.findByIdCustom(uuid);
            if(niveauAcademiqueFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(niveauAcademiqueForUpdate.code!=null) niveauAcademiqueFind.code = niveauAcademiqueForUpdate.code;
            if(niveauAcademiqueForUpdate.libelle!=null) niveauAcademiqueFind.libelle = niveauAcademiqueForUpdate.libelle;
            if(niveauAcademiqueForUpdate.description!=null) niveauAcademiqueFind.description = niveauAcademiqueForUpdate.description;
            this.persist(niveauAcademiqueFind);
            return niveauAcademiqueFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        NiveauAcademique niveauAcademique = this.findByIdCustom(uuid);
        if(niveauAcademique==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        niveauAcademique.status = ParametersConfig.status_delete;
        this.persist(niveauAcademique);
        return this.findByIdCustom(uuid)==null;
    }
}
