package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VTypeSituationAcademique;

@Path("v1/academicstatustypes/views/")
public class VTypeSituationAcademiqueResource extends BasicResourceDtoForView<VTypeSituationAcademique, ProfessionDto> {
}

