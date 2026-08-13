package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Sexe;

import jakarta.ws.rs.Path;

@Tag(name = "Sexes", description = "Référentiel des sexes.")
@Path("v1/sexes")
public class SexeResource extends BasicResource_Hold<Sexe> {
}
