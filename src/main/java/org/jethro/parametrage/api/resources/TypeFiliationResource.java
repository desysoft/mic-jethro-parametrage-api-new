package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TypeFiliation;

import jakarta.ws.rs.Path;

@Tag(name = "Types filiation", description = "Référentiel des types de filiation (Père, Mère, Frère, Sœur, Fils, Fille, Époux, Épouse, Apparenté(e)).")
@Path("v1/typefiliations")
public class TypeFiliationResource extends BasicResource<TypeFiliation> {
}
