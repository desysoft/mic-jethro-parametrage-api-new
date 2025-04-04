package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VSexe;

@Path("v1/sexes/views/")
public class VSexeResource extends BasicResourceDtoForView<VSexe, ProfessionDto> {
}

