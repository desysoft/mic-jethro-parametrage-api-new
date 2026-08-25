package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.CategorieProfession;

import jakarta.ws.rs.Path;

@Tag(name = "Catégories de profession", description = "Référentiel des catégories de profession.")
@Path("/v1/categories-profession")
public class CategorieProfessionResource extends BasicResource<CategorieProfession> {
}
