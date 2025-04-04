package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.Municipality;
import org.jethro.parametrage.api.entities.views.VCommune;

@Path("v1/municipalities/views/")
public class VCommuneResource extends BasicResourceDtoForView<VCommune, Municipality> {
}

