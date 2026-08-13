package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.NeighborhoodDto;
import org.jethro.parametrage.api.entities.views.VQuartier;

@Tag(name = "Vues — Quartiers", description = "Vue en lecture consolidée des quartiers.")
@Path("v1/neighborhoods/views/")
public class VQuartierResource extends BasicResourceDtoForView<VQuartier, NeighborhoodDto> {
}
