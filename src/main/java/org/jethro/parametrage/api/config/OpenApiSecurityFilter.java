package org.jethro.parametrage.api.config;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;

/**
 * Ajoute l'exigence de securite globale (schema "SecurityScheme") au document
 * OpenAPI, sauf si openapi.security.enabled=false. Permet de desactiver le
 * bouton Authorize de Swagger UI sans toucher au code (juste redemarrer avec
 * la propriete/variable d'environnement modifiee) : utile par exemple pour
 * tester rapidement un endpoint sans token.
 * Enregistre via la propriete mp.openapi.filter (application.properties).
 */
public class OpenApiSecurityFilter implements OASFilter {

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        boolean enabled = ConfigProvider.getConfig()
            .getOptionalValue("openapi.security.enabled", Boolean.class)
            .orElse(true);

        if (enabled) {
            openAPI.addSecurityRequirement(
                OASFactory.createSecurityRequirement().addScheme("SecurityScheme"));
        }
    }
}
