package org.jethro.parametrage.api.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.TypeSituationAcademiqueDao;
import org.jethro.parametrage.api.entities.TypeSituationAcademique;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class TypeSituationAcademiqueDaoImpl extends CommonDao<TypeSituationAcademique> implements
    TypeSituationAcademiqueDao {


    public TypeSituationAcademique save(TypeSituationAcademique typeSituationAcademique){
        try {
            LOG.info("save");
            if(typeSituationAcademique.code == null) {
                typeSituationAcademique.code = ToolString.getComplexId();
            }
            if(this.isExistCode(typeSituationAcademique.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(typeSituationAcademique);
            return typeSituationAcademique;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TypeSituationAcademique update(String uuid, TypeSituationAcademique typeSituationAcademiqueForUpdate){
        try {
            TypeSituationAcademique typeSituationAcademiqueFind = this.findByIdCustom(uuid);
            if(typeSituationAcademiqueFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(typeSituationAcademiqueForUpdate.code!=null) typeSituationAcademiqueFind.code = typeSituationAcademiqueForUpdate.code;
            if(typeSituationAcademiqueForUpdate.libelle!=null) typeSituationAcademiqueFind.libelle = typeSituationAcademiqueForUpdate.libelle;
            if(typeSituationAcademiqueForUpdate.description!=null) typeSituationAcademiqueFind.description = typeSituationAcademiqueForUpdate.description;
            this.persist(typeSituationAcademiqueFind);
            return typeSituationAcademiqueFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        TypeSituationAcademique typeSituationAcademique = this.findByIdCustom(uuid);
        if(typeSituationAcademique==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        typeSituationAcademique.status = ParametersConfig.status_delete;
        this.persist(typeSituationAcademique);
        return this.findByIdCustom(uuid)==null;
    }
}
