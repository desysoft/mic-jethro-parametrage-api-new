package org.jethro.parametrage.api.dao.impl;

import org.jethro.parametrage.api.dao.CardinaliteDao;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.entities.Cardinalite;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class CardinaliteDaoImpl extends CommonDao<Cardinalite> implements
    CardinaliteDao {

    public Cardinalite save(Cardinalite cardinalite){
        try {
            LOG.info("save");
            if(cardinalite.code == null) {
                cardinalite.code = ToolString.getComplexId(ParametersConfig.CARDINALITE_CODE_PREFIXE);
            }
            if(this.isExistCode(cardinalite.code)){
                LOG.info("isExistCode");
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.codeAlreadyExist);
                return null;
            }
            this.persist(cardinalite);
            return cardinalite;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Cardinalite update(String uuid, Cardinalite cardinaliteForUpdate){
        try {
            Cardinalite cardinaliteFind = this.findByIdCustom(uuid);
            if(cardinaliteFind==null){
                this.setMessage(ParametersConfig.PROCESS_FAILED);
                this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
                return null;
            }

            if(cardinaliteForUpdate.code!=null) cardinaliteFind.code = cardinaliteForUpdate.code;
            if(cardinaliteForUpdate.libelle!=null) cardinaliteFind.libelle = cardinaliteForUpdate.libelle;
            if(cardinaliteForUpdate.description!=null) cardinaliteFind.description = cardinaliteForUpdate.description;
            this.persist(cardinaliteFind);
            return cardinaliteFind;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String uuid){
        Cardinalite cardinalite = this.findByIdCustom(uuid);
        if(cardinalite==null){
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
            return false;
        }

        cardinalite.status = ParametersConfig.status_delete;
        this.persist(cardinalite);
        return this.findByIdCustom(uuid)==null;
    }
}
