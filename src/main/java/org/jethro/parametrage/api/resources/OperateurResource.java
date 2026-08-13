package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Operateur;

import jakarta.ws.rs.Path;

@Tag(name = "Opérateurs", description = "Référentiel des opérateurs téléphoniques.")
@Path("v1/operateurs")
public class OperateurResource extends BasicResource_Hold<Operateur> {
}
