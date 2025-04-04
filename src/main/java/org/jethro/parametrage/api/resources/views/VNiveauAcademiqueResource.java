package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.NiveauAcademiqueDto;
import org.jethro.parametrage.api.entities.views.VNiveauAcademique;

@Path("v1/academiclevels/views/")
public class VNiveauAcademiqueResource extends BasicResourceDtoForView<VNiveauAcademique, NiveauAcademiqueDto> {
}

