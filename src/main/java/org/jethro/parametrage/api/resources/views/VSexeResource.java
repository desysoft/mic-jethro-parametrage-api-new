package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.SexeDto;
import org.jethro.parametrage.api.entities.views.VSexe;

@Path("v1/sexes/views/")
public class VSexeResource extends BasicResourceDtoForView<VSexe, SexeDto> {
}

