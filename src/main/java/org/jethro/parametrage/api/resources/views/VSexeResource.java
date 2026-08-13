package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.SexeDto;
import org.jethro.parametrage.api.entities.views.VSexe;

@Tag(name = "Vues — Sexes", description = "Vue en lecture consolidée des sexes.")
@Path("v1/sexes/views/")
public class VSexeResource extends BasicResourceDtoForView<VSexe, SexeDto> {
}

