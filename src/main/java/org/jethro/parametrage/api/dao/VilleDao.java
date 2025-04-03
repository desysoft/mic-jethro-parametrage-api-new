package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.entities.Ville;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VilleDao extends CommonDao<Ville> {

    public Ville save(Ville ville){
        try {
            LOG.info("save");
            if(this.isExistCode(ville.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(ville);
            return ville;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Ville update(String uuid, Ville villeForUpdate){
        try {
            Ville villeFind = this.findByIdCustom(uuid);
            if(villeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }
            if(villeForUpdate.code!=null) villeFind.code = villeForUpdate.code;
            if(villeForUpdate.libelle!=null) villeFind.libelle = villeForUpdate.libelle;
            if(villeForUpdate.description!=null) villeFind.description = villeForUpdate.description;
            this.persist(villeFind);
            return villeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Ville ville = this.findByIdCustom(uuid);
        if(ville==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        ville.status = ParametersConfig.status_delete;
        this.persist(ville);
        return this.findByIdCustom(uuid)==null;
    }
}
