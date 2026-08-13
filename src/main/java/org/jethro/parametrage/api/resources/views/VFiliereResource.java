package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.FiliereDto;
import org.jethro.parametrage.api.entities.views.VFiliere;

@Tag(name = "Vues — Filières", description = "Vue en lecture consolidée des filières.")
@Path("v1/pathways/views/")
public class VFiliereResource extends BasicResourceDtoForView<VFiliere, FiliereDto> {
}
