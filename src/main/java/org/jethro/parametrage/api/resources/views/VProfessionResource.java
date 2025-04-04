package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.jethro.parametrage.api.dto.ProfessionDto;
import org.jethro.parametrage.api.entities.views.VProfession;

@Path("v1/professions/views/")
public class VProfessionResource extends BasicResourceDtoForView<VProfession, ProfessionDto> {
}
