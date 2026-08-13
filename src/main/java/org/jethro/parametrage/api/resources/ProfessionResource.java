package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Profession;

import jakarta.ws.rs.Path;

@Tag(name = "Professions", description = "Référentiel des professions.")
@Path("/v1/professions")
public class ProfessionResource extends BasicResource<Profession> {
}
