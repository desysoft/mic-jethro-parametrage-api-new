package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VDiplome;

@Path("v1/degrees/views/")
public class VDiplomeResource extends BasicResourceDtoForView<VDiplome, ProfessionDto> {
}

