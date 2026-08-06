package org.jethro.parametrage.api.dao;

import jakarta.enterprise.context.RequestScoped;

/**
 * Porte le message/detailMessage d'une opération DAO (create/update/delete) pour la
 * requête HTTP courante. Injecté à la fois dans AbstractDao (qui l'alimente) et dans
 * BasicResource_Hold (qui le lit pour renvoyer la cause d'un échec de suppression) :
 * sans ce partage par requête, les champs message/detailMessage vivaient directement
 * sur les DAO en @ApplicationScoped, donc étaient partagés entre toutes les requêtes
 * concurrentes au lieu d'être propres à chacune.
 */
@RequestScoped
public class OperationFeedback {

    private String message;
    private String detailMessage;

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
}
