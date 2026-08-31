package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Cardinalite;

import jakarta.ws.rs.Path;

@Tag(name = "Cardinalités", description = "Référentiel des cardinalités de type de filiation (Unique, Multiple).")
@Path("v1/cardinalites")
public class CardinaliteResource extends BasicResource<Cardinalite> {
}
