package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.NeighborhoodDto;
import org.jethro.parametrage.api.entities.views.VQuartier;

@Path("v1/neighborhoods/views/")
public class VQuartierResource extends BasicResourceDtoForView<VQuartier, NeighborhoodDto> {
}
