package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Filiere;

@Tag(name = "Filières", description = "Référentiel des filières de formation.")
@Path("/v1/pathways")
public class FiliereResource extends BasicResource<Filiere> {
}
