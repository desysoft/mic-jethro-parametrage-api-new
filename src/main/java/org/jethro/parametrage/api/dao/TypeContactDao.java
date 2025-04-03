package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.entities.TypeContact;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TypeContactDao extends CommonDao<TypeContact> {

    public TypeContact save(TypeContact typeContact){
        try {
            LOG.info("save");
            if(this.isExistCode(typeContact.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(typeContact);
            return typeContact;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TypeContact update(String uuid, TypeContact typeContactForUpdate){
        try {
            TypeContact typeContactFind = this.findByIdCustom(uuid);
            if(typeContactFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(typeContactForUpdate.code!=null) typeContactFind.code = typeContactForUpdate.code;
            if(typeContactForUpdate.libelle!=null) typeContactFind.libelle = typeContactForUpdate.libelle;
            if(typeContactForUpdate.description!=null) typeContactFind.description = typeContactForUpdate.description;
            this.persist(typeContactFind);
            return typeContactFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        TypeContact typeContact = this.findByIdCustom(uuid);
        if(typeContact==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        typeContact.status = ParametersConfig.status_delete;
        this.persist(typeContact);
        return this.findByIdCustom(uuid)==null;
    }
}
