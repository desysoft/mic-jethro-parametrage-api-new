package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Ville;

import jakarta.ws.rs.*;

@Tag(name = "Villes", description = "Référentiel des villes.")
@Path("/v1/villes")
public class VilleResource extends BasicResource_Hold<Ville> {
}
