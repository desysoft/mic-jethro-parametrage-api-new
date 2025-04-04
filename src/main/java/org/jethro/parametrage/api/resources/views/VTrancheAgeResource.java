package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VTrancheAge;

@Path("v1/sliceages/views/")
public class VTrancheAgeResource extends BasicResourceDtoForView<VTrancheAge, ProfessionDto> {
}

