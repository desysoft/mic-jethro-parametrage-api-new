package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.SituationMatrimoniale;

import jakarta.ws.rs.Path;

@Tag(name = "Situations matrimoniales", description = "Référentiel des situations matrimoniales.")
@Path("v1/maritalstatus")
public class SituationMatrimonialeResource extends BasicResource<SituationMatrimoniale> {
}
