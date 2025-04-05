package org.jethro.parametrage.api.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.TypeTravailleurDao;
import org.jethro.parametrage.api.entities.TypeTravailleur;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class TypeTravailleurDaoImpl extends CommonDao<TypeTravailleur> implements
    TypeTravailleurDao {


    public TypeTravailleur save(TypeTravailleur typeTravailleur){
        try {
            LOG.info("save");
            if(typeTravailleur.code == null) {
                typeTravailleur.code = ToolString.getComplexId(ParametersConfig.WORKER_TYPE_CODE_PREFIXE);
            }
            if(this.isExistCode(typeTravailleur.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(typeTravailleur);
            return typeTravailleur;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TypeTravailleur update(String uuid, TypeTravailleur typeTravailleurForUpdate){
        try {
            TypeTravailleur typeTravailleurFind = this.findByIdCustom(uuid);
            if(typeTravailleurFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(typeTravailleurForUpdate.code!=null) typeTravailleurFind.code = typeTravailleurForUpdate.code;
            if(typeTravailleurForUpdate.libelle!=null) typeTravailleurFind.libelle = typeTravailleurForUpdate.libelle;
            if(typeTravailleurForUpdate.description!=null) typeTravailleurFind.description = typeTravailleurForUpdate.description;
            this.persist(typeTravailleurFind);
            return typeTravailleurFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String uuid){
        TypeTravailleur typeTravailleur = this.findByIdCustom(uuid);
        if(typeTravailleur==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        typeTravailleur.status = ParametersConfig.status_delete;
        this.persist(typeTravailleur);
        return this.findByIdCustom(uuid)==null;
    }
}
