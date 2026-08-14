package org.jethro.parametrage.api.config;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.jethro.parametrage.api.dao.InstitutionContext;

/**
 * Capture le header institutionId de la requête courante dans InstitutionContext
 * (@RequestScoped), lu ensuite par CommonDao pour filtrer les lectures et peupler
 * pkeyInstitutionId à la création. Header absent ou vide = comportement inchangé :
 * InstitutionContext reste à null, aucun filtrage appliqué, conforme au contrat REST
 * existant (mic-members-managers-api n'envoie pas ce header aujourd'hui).
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class InstitutionRequestFilter implements ContainerRequestFilter {

    public static final String HEADER_NAME = "institutionId";

    @Inject
    InstitutionContext institutionContext;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String institutionId = requestContext.getHeaderString(HEADER_NAME);
        if (institutionId != null && !institutionId.isBlank()) {
            institutionContext.setInstitutionId(institutionId.trim());
        }
    }
}
