package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.entities.Commune;
import org.jethro.parametrage.api.services.CommuneService;

import jakarta.ws.rs.*;
import java.util.List;


@Path("/v1/municipalities")
public class CommuneResource extends BasicResource_Hold<Commune> {

    CommuneService communeService = null;

    @GET
    @Path("ville/{id}")
    public List<Commune> obtenirLesCommunes(@PathParam("id") String idVille
            , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        communeService = (CommuneService) this.getService();
        return communeService.obtenirListeDesCommunesParVille(idVille,pageIndex,pageSize);
    }
}
