package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.TypeTravailleurDto;
import org.jethro.parametrage.api.entities.views.VTypeTravailleur;

@Tag(name = "Vues — Types de travailleur", description = "Vue en lecture consolidée des types de travailleur.")
@Path("v1/workertypes/views/")
public class VTypeTravailleurResource extends BasicResourceDtoForView<VTypeTravailleur, TypeTravailleurDto> {
}

