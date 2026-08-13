package org.jethro.parametrage.api.resources;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.TrancheAge;

@Tag(name = "Tranches d'âge", description = "Référentiel des tranches d'âge.")
@Path("/v1/sliceages")
public class TrancheAgeResource extends BasicResource<TrancheAge> {
}
