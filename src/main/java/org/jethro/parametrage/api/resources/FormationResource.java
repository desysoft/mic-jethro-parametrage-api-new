package org.jethro.parametrage.api.resources;

import io.quarkus.security.Authenticated;
import org.jethro.parametrage.api.services.FormationService;
import org.jethro.parametrage.api.entities.Formation;

import jakarta.ws.rs.*;
import java.util.List;


@Path("/v1/formations")
public class FormationResource extends BasicResource_Hold<Formation> {

    FormationService formationService = null;

    @GET
    @Path("type/{id}")
    public List<Formation> obtenirLesFormations(@PathParam("id") String idFormationType
            , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        formationService = (FormationService) this.getService();
        return formationService.getFormationByTypeFormation(idFormationType,pageIndex,pageSize);
    }
}
