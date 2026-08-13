package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.Municipality;
import org.jethro.parametrage.api.entities.views.VCommune;

@Tag(name = "Vues — Communes", description = "Vue en lecture consolidée des communes (jointures pré-calculées).")
@Path("v1/municipalities/views/")
public class VCommuneResource extends BasicResourceDtoForView<VCommune, Municipality> {
}

