package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.ProfessionDao;
import org.jethro.parametrage.api.entities.Profession;
import org.jethro.parametrage.api.exceptions.CodeExistException;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class ProfessionDaoImpl extends CommonDao<Profession> implements ProfessionDao {

    public Profession save(Profession profession) throws CodeExistException {
        try {
            LOG.info("save");
            if(profession.code == null) {
                profession.code = ToolString.getComplexId();
            }
            if(this.isExistCode(profession.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                throw new CodeExistException(this.getDetailMessage());
            }

            this.persist(profession);
            return profession;
        }catch (Exception e){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            throw new RuntimeException(e);
        }
    }

    public Profession update(String uuid, Profession professionForUpdate){
        try {
            Profession professionFind = this.findByIdCustom(uuid);
            if(professionFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(professionForUpdate.code!=null) professionFind.code = professionForUpdate.code;
            if(professionForUpdate.libelle!=null) professionFind.libelle = professionForUpdate.libelle;
            if(professionForUpdate.description!=null) professionFind.description = professionForUpdate.description;
            this.persist(professionFind);
            return professionFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        try {
            Profession profession = this.findByIdCustom(uuid);
            if(profession==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return false;
            }
            profession.delete();
            return this.findByIdCustom(uuid)==null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
