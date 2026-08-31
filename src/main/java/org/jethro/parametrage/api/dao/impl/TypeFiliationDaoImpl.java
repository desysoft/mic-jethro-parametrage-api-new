package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.TypeFiliationDao;
import org.jethro.parametrage.api.entities.TypeFiliation;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class TypeFiliationDaoImpl extends CommonDao<TypeFiliation> implements
    TypeFiliationDao {

    public TypeFiliation save(TypeFiliation typeFiliation){
        try {
            LOG.info("save");
            if(typeFiliation.code == null) {
                typeFiliation.code = ToolString.getComplexId(ParametersConfig.TYPE_FILIATION_CODE_PREFIXE);
            }
            if(this.isExistCode(typeFiliation.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(typeFiliation);
            return typeFiliation;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TypeFiliation update(String uuid, TypeFiliation typeFiliationForUpdate){
        try {
            TypeFiliation typeFiliationFind = this.findByIdCustom(uuid);
            if(typeFiliationFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(typeFiliationForUpdate.code!=null) typeFiliationFind.code = typeFiliationForUpdate.code;
            if(typeFiliationForUpdate.libelle!=null) typeFiliationFind.libelle = typeFiliationForUpdate.libelle;
            if(typeFiliationForUpdate.description!=null) typeFiliationFind.description = typeFiliationForUpdate.description;
            if(typeFiliationForUpdate.cardinalite!=null) typeFiliationFind.cardinalite = typeFiliationForUpdate.cardinalite;
            if(typeFiliationForUpdate.sens!=null) typeFiliationFind.sens = typeFiliationForUpdate.sens;
            if(typeFiliationForUpdate.contrainteSexe!=null) typeFiliationFind.contrainteSexe = typeFiliationForUpdate.contrainteSexe;
            this.persist(typeFiliationFind);
            return typeFiliationFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        TypeFiliation typeFiliation = this.findByIdCustom(uuid);
        if(typeFiliation==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        typeFiliation.status = ParametersConfig.status_delete;
        this.persist(typeFiliation);
        return this.findByIdCustom(uuid)==null;
    }
}
