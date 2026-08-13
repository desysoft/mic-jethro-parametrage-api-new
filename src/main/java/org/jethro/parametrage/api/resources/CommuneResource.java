package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.services.CommuneService;

import jakarta.ws.rs.*;
import java.util.List;


@Tag(name = "Communes", description = "Référentiel des communes, rattachées à une ville.")
@Path("/v1/municipalities")
public class CommuneResource extends BasicResource_Hold<Commune> {

    CommuneService communeService = null;

    @Operation(summary = "Liste les communes d'une ville")
    @GET
    @Path("ville/{id}")
    public List<Commune> obtenirLesCommunes(
            @Parameter(description = "uuid de la ville", required = true) @PathParam("id") String idVille
            , @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        communeService = (CommuneService) this.getService();
        return communeService.obtenirListeDesCommunesParVille(idVille,pageIndex,pageSize);
    }
}
