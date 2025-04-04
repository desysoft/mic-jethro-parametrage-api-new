package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VTypeTravailleur;

@Path("v1/workertypes/views/")
public class VTypeTravailleurResource extends BasicResourceDtoForView<VTypeTravailleur, ProfessionDto> {
}

