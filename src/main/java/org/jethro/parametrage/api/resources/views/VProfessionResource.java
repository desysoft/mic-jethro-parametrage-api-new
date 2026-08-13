package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VProfession;

@Tag(name = "Vues — Professions", description = "Vue en lecture consolidée des professions.")
@Path("v1/professions/views/")
public class VProfessionResource extends BasicResourceDtoForView<VProfession, ProfessionDto> {
}
