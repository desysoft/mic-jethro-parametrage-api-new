package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TypeTravailleur;

@Tag(name = "Types de travailleur", description = "Référentiel des types de travailleur.")
@Path("/v1/workertypes")
public class TypeTravailleurResource extends BasicResource<TypeTravailleur> {
}
