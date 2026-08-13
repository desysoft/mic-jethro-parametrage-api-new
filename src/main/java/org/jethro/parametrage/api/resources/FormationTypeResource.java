package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.FormationType;

import jakarta.ws.rs.Path;

@Tag(name = "Types de formation", description = "Référentiel des types de formation.")
@Path("/v1/formationtypes")
public class FormationTypeResource extends BasicResource_Hold<FormationType> {
}
