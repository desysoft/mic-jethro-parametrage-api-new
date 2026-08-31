package org.jethro.parametrage.api.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.coherence.CoherenceResult;
import org.jethro.parametrage.api.coherence.TypeFiliationCoherenceChecker;

@Tag(name = "Filiation — vérification de cohérence", description = "Écart entre les tables cardinalite/sens/contraintesexe et leur enum miroir.")
@Path("v1/typefiliation-config/coherence")
@Produces(MediaType.APPLICATION_JSON)
public class TypeFiliationCoherenceResource {

    @Inject
    TypeFiliationCoherenceChecker checker;

    @Operation(summary = "Vérifie la cohérence entre cardinalite/sens/contraintesexe et leur enum Java miroir. Appelé par le frontend à l'ouverture des écrans liés à la filiation.")
    @GET
    public CoherenceResult verifier() {
        return checker.check();
    }
}
