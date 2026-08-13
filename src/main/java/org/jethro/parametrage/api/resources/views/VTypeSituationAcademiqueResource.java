package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.TypeSituationAcademiqueDto;
import org.jethro.parametrage.api.entities.views.VTypeSituationAcademique;

@Tag(name = "Vues — Types de situation académique", description = "Vue en lecture consolidée des types de situation académique.")
@Path("v1/academicstatustypes/views/")
public class VTypeSituationAcademiqueResource extends BasicResourceDtoForView<VTypeSituationAcademique, TypeSituationAcademiqueDto> {
}

