package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.DiplomeDto;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VDiplome;

@Tag(name = "Vues — Diplômes", description = "Vue en lecture consolidée des diplômes.")
@Path("v1/degrees/views/")
public class VDiplomeResource extends BasicResourceDtoForView<VDiplome, DiplomeDto> {
}

