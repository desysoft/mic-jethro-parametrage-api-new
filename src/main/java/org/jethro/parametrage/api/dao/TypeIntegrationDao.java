package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.entities.TypeIntegration;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TypeIntegrationDao extends CommonDao<TypeIntegration> {

    public TypeIntegration save(TypeIntegration typeIntegration){
        try {
            LOG.info("save");
            if(this.isExistCode(typeIntegration.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(typeIntegration);
            return typeIntegration;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TypeIntegration update(String uuid, TypeIntegration typeIntegrationForUpdate){
        try {
            TypeIntegration typeIntegrationFind = this.findByIdCustom(uuid);
            if(typeIntegrationFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(typeIntegrationForUpdate.code!=null) typeIntegrationFind.code = typeIntegrationForUpdate.code;
            if(typeIntegrationForUpdate.libelle!=null) typeIntegrationFind.libelle = typeIntegrationForUpdate.libelle;
            if(typeIntegrationForUpdate.description!=null) typeIntegrationFind.description = typeIntegrationForUpdate.description;
            this.persist(typeIntegrationFind);
            return typeIntegrationFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        TypeIntegration typeIntegration = this.findByIdCustom(uuid);
        if(typeIntegration==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        typeIntegration.status = ParametersConfig.status_delete;
        this.persist(typeIntegration);
        return this.findByIdCustom(uuid)==null;
    }
}
