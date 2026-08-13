package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TypeSituationAcademique;

@Tag(name = "Types de situation académique", description = "Référentiel des types de situation académique.")
@Path("/v1/academicstatustypes")
public class TypeSituationAcademiqueResource extends BasicResource<TypeSituationAcademique> {
}
