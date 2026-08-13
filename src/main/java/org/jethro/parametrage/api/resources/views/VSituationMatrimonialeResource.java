package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.SituationMatrimonialeDto;
import org.jethro.parametrage.api.entities.views.VSituationMatrimoniale;

@Tag(name = "Vues — Situations matrimoniales", description = "Vue en lecture consolidée des situations matrimoniales.")
@Path("v1/maritalstatus/views/")
public class VSituationMatrimonialeResource extends BasicResourceDtoForView<VSituationMatrimoniale, SituationMatrimonialeDto> {
}

