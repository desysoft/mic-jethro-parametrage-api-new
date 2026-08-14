package org.jethro.parametrage.api.dao;


import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public abstract class AbstractDao {

    /**
     * @RequestScoped plutôt que des champs directs : les DAO sont @ApplicationScoped
     * (instance unique partagée par toutes les requêtes), donc stocker message/
     * detailMessage directement dessus les faisait fuiter entre requêtes concurrentes.
     */
    @Inject
    OperationFeedback operationFeedback;

    @Inject
    InstitutionContext institutionContext;

    @Inject
    EntityManager em;



    public String getMessage() {
        return operationFeedback.getMessage();
    }

    public void setMessage(String message) {
        operationFeedback.setMessage(message);
    }

    public String getDetailMessage() {
        return operationFeedback.getDetailMessage();
    }

    public void setDetailMessage(String detailMessage) {
        operationFeedback.setDetailMessage(detailMessage);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }


    /**
     * Institution courante résolue depuis le header X-Institution-Id (InstitutionRequestFilter),
     * ou null si absente. Null = aucun filtrage, conforme au comportement historique.
     */
    public String getCurrentInstitutionId() {
        return institutionContext.getInstitutionId();
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
