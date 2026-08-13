package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.NiveauAcademiqueDto;
import org.jethro.parametrage.api.entities.views.VNiveauAcademique;

@Tag(name = "Vues — Niveaux académiques", description = "Vue en lecture consolidée des niveaux académiques.")
@Path("v1/academiclevels/views/")
public class VNiveauAcademiqueResource extends BasicResourceDtoForView<VNiveauAcademique, NiveauAcademiqueDto> {
}

