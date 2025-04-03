package org.jethro.parametrage.api.dao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CommuneDao extends CommonDao<Commune> {

    public List<Commune> getByVille(String idVille, int pageIndex, int pageSize){
        Map<String, Object> params = new HashMap<>();
        idVille = checkAndGetForSearchValue(idVille);
        params.put("idVille", idVille);
        PanacheQuery<Commune> panacheQuery =  find("ville.uuid LIKE ?1 and status = ?2",idVille, ParametersConfig.status_enable);
        if(pageSize==0){
            return panacheQuery.list();
        }else {
            return panacheQuery.page(pageIndex,pageSize).list();
        }
    }


    public Commune save(Commune commune){
        try {
            LOG.info("save");
            if(this.isExistCode(commune.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(commune);
            return commune;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Commune update(String uuid, Commune communeForUpdate){
        try {
            Commune communeFind = this.findByIdCustom(uuid);
            if(communeFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(communeForUpdate.code!=null) communeFind.code = communeForUpdate.code;
            if(communeForUpdate.libelle!=null) communeFind.libelle = communeForUpdate.libelle;
            if(communeForUpdate.description!=null) communeFind.description = communeForUpdate.description;
            if(communeForUpdate.ville!=null) communeFind.ville = communeForUpdate.ville;
            this.persist(communeFind);
            return communeFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Commune commune = this.findByIdCustom(uuid);
        if(commune==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        commune.status = ParametersConfig.status_delete;
        this.persist(commune);
        return this.findByIdCustom(uuid)==null;
    }
}
