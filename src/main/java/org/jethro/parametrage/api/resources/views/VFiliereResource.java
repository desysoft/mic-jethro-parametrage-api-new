package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.FiliereDto;
import org.jethro.parametrage.api.entities.views.VFiliere;

@Path("v1/pathways/views/")
public class VFiliereResource extends BasicResourceDtoForView<VFiliere, FiliereDto> {
}
