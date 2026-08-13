package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.TrancheAgeDto;
import org.jethro.parametrage.api.entities.views.VTrancheAge;

@Tag(name = "Vues — Tranches d'âge", description = "Vue en lecture consolidée des tranches d'âge.")
@Path("v1/sliceages/views/")
public class VTrancheAgeResource extends BasicResourceDtoForView<VTrancheAge, TrancheAgeDto> {
}

