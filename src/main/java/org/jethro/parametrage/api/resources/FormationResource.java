package org.jethro.parametrage.api.resources;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.services.FormationService;
import org.jethro.parametrage.api.entities.Formation;

import jakarta.ws.rs.*;
import java.util.List;


@Tag(name = "Formations", description = "Référentiel des formations, rattachées à un type de formation.")
@Path("/v1/formations")
public class FormationResource extends BasicResource_Hold<Formation> {

    FormationService formationService = null;

    @Operation(summary = "Liste les formations d'un type de formation donné")
    @GET
    @Path("type/{id}")
    public List<Formation> obtenirLesFormations(
            @Parameter(description = "uuid du type de formation", required = true) @PathParam("id") String idFormationType
            , @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        formationService = (FormationService) this.getService();
        return formationService.getFormationByTypeFormation(idFormationType,pageIndex,pageSize);
    }
}
