package org.jethro.parametrage.api.dao;


import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public abstract class AbstractDao {

    String message;
    String detailMessage;
    @Inject
    EntityManager em;

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }


    public String checkAndGetForSearchValue(String search_value){
        return (search_value==null || search_value.equals(""))?"%":"%"+search_value+"%";
    }

    public void setParametersMessageNotFound(String optionalMessage){
        optionalMessage = (optionalMessage==null || optionalMessage.equals(""))?"":" : "+optionalMessage;
        this.setMessage(ParametersConfig.PROCESS_FAILED);
        this.setDetailMessage(ParametersConfig.genericNotFoundMessage+optionalMessage);
    }

    public void setParametersMessageDelete(Boolean isSuccess, String optionalMessage){
        optionalMessage = (optionalMessage==null || optionalMessage.equals(""))?"":" : "+optionalMessage;
        if(isSuccess){
            this.setMessage(ParametersConfig.PROCESS_SUCCES);
            this.setDetailMessage(ParametersConfig.SUCCES_DELETE);
        }else {
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.FAILED_DELETE+optionalMessage);
        }
    }

    public void setParametersMessageUpdate(Boolean isSuccess, String optionalMessage){
        optionalMessage = (optionalMessage==null || optionalMessage.equals(""))?"":" : "+optionalMessage;
        if(isSuccess){
            this.setMessage(ParametersConfig.PROCESS_SUCCES);
            this.setDetailMessage(ParametersConfig.SUCCES_UPDATE);
        }else {
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.FAILED_UPDATE+optionalMessage);
        }
    }

    public void setParametersMessageCreate(Boolean isSuccess, String optionalMessage){
        optionalMessage = (optionalMessage==null || optionalMessage.equals(""))?"":" : "+optionalMessage;
        if(isSuccess){
            this.setMessage(ParametersConfig.PROCESS_SUCCES);
            this.setDetailMessage(ParametersConfig.SUCCES_CREATE);
        }else {
            this.setMessage(ParametersConfig.PROCESS_FAILED);
            this.setDetailMessage(ParametersConfig.FAILED_CREATE+optionalMessage);
        }
    }
}
