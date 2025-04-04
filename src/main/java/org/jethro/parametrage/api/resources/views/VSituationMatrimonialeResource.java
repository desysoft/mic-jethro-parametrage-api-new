package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VSituationMatrimoniale;

@Path("v1/maritalstatus/views/")
public class VSituationMatrimonialeResource extends BasicResourceDtoForView<VSituationMatrimoniale, ProfessionDto> {
}

