package org.jethro.parametrage.api.resources.views;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.CategorieProfessionDto;
import org.jethro.parametrage.api.entities.views.VCategorieProfession;

@Tag(name = "Vues — Catégories de profession", description = "Vue en lecture consolidée des catégories de profession.")
@Path("v1/categories-profession/views/")
public class VCategorieProfessionResource extends BasicResourceDtoForView<VCategorieProfession, CategorieProfessionDto> {
}
