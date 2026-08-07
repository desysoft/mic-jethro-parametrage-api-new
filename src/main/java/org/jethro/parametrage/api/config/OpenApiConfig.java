package org.jethro.parametrage.api.config;

import jakarta.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

/**
 * L'exigence de securite (schema "SecurityScheme") appliquee a toutes les
 * operations est ajoutee dynamiquement par OpenApiSecurityFilter, pilotee par
 * la propriete openapi.security.enabled : voir ce filtre pour le detail.
 */
@OpenAPIDefinition(
    info = @Info(title = "mic-jethro-parametrage-api API", version = "1.0.0-SNAPSHOT")
)
public class OpenApiConfig extends Application {
}
