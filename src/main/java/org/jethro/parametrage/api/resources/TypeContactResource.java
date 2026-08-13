package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TypeContact;

import jakarta.ws.rs.Path;

@Tag(name = "Types de contact", description = "Référentiel des types de contact.")
@Path("v1/type-contacts")
public class TypeContactResource extends BasicResource_Hold<TypeContact> {
}
