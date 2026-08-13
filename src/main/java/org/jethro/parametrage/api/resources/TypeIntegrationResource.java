package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TypeIntegration;

@Tag(name = "Types d'intégration", description = "Référentiel des types d'intégration.")
@Path("v1/type-integrations")
public class TypeIntegrationResource extends BasicResource_Hold<TypeIntegration> {
}
