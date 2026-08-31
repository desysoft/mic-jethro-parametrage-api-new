package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Sens;

import jakarta.ws.rs.Path;

@Tag(name = "Sens", description = "Référentiel des sens de type de filiation (Ascendant, Descendant, Même génération, Union).")
@Path("v1/sens")
public class SensResource extends BasicResource<Sens> {
}
