package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Diplome;

@Tag(name = "Diplômes", description = "Référentiel des diplômes académiques.")
@Path("/v1/degrees")
public class DiplomeResource extends BasicResource<Diplome> {
}
