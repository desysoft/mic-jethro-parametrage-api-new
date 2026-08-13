package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.NiveauAcademique;

@Tag(name = "Niveaux académiques", description = "Référentiel des niveaux académiques.")
@Path("/v1/academiclevels")
public class NiveauAcademiqueResource extends BasicResource<NiveauAcademique> {
}
