package org.jethro.parametrage.api.dao;

import jakarta.enterprise.context.RequestScoped;

/**
 * Porte l'institution courante (pkeyInstitutionId) pour la requête HTTP en cours, résolue
 * par InstitutionRequestFilter depuis le header X-Institution-Id. Même raison d'être que
 * OperationFeedback : les DAO sont @ApplicationScoped (instance unique partagée), donc
 * porter cette valeur directement dessus la ferait fuiter entre requêtes concurrentes.
 * Institution absente (null) = comportement inchangé : aucun filtrage, aucune institution
 * imposée à la création.
 */
@RequestScoped
public class InstitutionContext {

    private String institutionId;

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }
}
