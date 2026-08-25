package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CategorieProfessionDao;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.entities.CategorieProfession;
import org.jethro.parametrage.api.exceptions.CodeExistException;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class CategorieProfessionDaoImpl extends CommonDao<CategorieProfession> implements CategorieProfessionDao {

    public CategorieProfession save(CategorieProfession categorieProfession) throws CodeExistException {
        try {
            LOG.info("save");
            if(categorieProfession.code == null) {
                categorieProfession.code = ToolString.getComplexId(ParametersConfig.CATEGORIE_PROFESSION_CODE_PREFIXE);
            }
            if(this.isExistCode(categorieProfession.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                throw new CodeExistException(this.getDetailMessage());
            }

            this.persist(categorieProfession);
            return categorieProfession;
        }catch (Exception e){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            throw new RuntimeException(e);
        }
    }

    public CategorieProfession update(String uuid, CategorieProfession categorieProfessionForUpdate){
        try {
            CategorieProfession categorieProfessionFind = this.findByIdCustom(uuid);
            if(categorieProfessionFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(categorieProfessionForUpdate.code!=null) categorieProfessionFind.code = categorieProfessionForUpdate.code;
            if(categorieProfessionForUpdate.libelle!=null) categorieProfessionFind.libelle = categorieProfessionForUpdate.libelle;
            if(categorieProfessionForUpdate.description!=null) categorieProfessionFind.description = categorieProfessionForUpdate.description;
            this.persist(categorieProfessionFind);
            return categorieProfessionFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        try {
            CategorieProfession categorieProfession = this.findByIdCustom(uuid);
            if(categorieProfession==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return false;
            }
            categorieProfession.delete();
            return this.findByIdCustom(uuid)==null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
