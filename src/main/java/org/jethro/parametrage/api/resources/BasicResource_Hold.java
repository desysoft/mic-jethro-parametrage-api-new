package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jethro.parametrage.api.dao.OperationFeedback;
import org.jethro.parametrage.api.services.BasicCommonService;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResource_Hold<T> {

    @Inject
    BasicCommonService<T> service;

    @Inject
    OperationFeedback operationFeedback;

    @Operation(summary = "Liste paginée. pageSize=0 renvoie l'intégralité sans pagination.")
    @GET
    public List<T> obtenirListe(
            @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            ,@Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.obtenirListe(pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }


    @Operation(summary = "Recherche paginée par texte libre (paramètre query). pageSize=0 renvoie l'intégralité des résultats sans pagination.")
    @GET
    @Path("search")
    public List<T> rechercher(
            @Parameter(description = "texte recherché") @QueryParam("query") @DefaultValue("") String searchValue
            ,@Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            ,@Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.rechercher(searchValue,pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Operation(summary = "Recherche un élément par son identifiant technique (uuid).")
    @GET
    @Path("{id}")
    public T trouverParId(@Parameter(description = "uuid de l'élément", required = true) @PathParam("id") String id){
        return service.touverParId(id);
        //return this.trouverParId(id);
    }

    @Operation(summary = "Recherche un élément par son code métier unique.")
    @GET
    @Path("code/{code}")
    public T trouverParCode(@Parameter(description = "code métier de l'élément", required = true) @PathParam("code") String code){
        return service.touverParCode(code);
    }

    @Operation(summary = "Crée un nouvel élément.")
    @POST
    @Transactional
    public T ajouter(T t){
      try {
        return service.ajouter(t);
      } catch (Exception e) {
        throw new WebApplicationException(e);
      }
    }

    @Operation(summary = "Met à jour un élément existant.")
    @PUT
    @Path("{id}")
    @Transactional
    public T modifier(@Parameter(description = "uuid de l'élément à modifier", required = true) @PathParam("id") String id,T t){
        return service.modifer(id,t);
    }

    /**
     * Renvoie un statut 500 avec le detailMessage du DAO (via OperationFeedback) quand la
     * suppression échoue, au lieu d'un simple "false" : sans quoi le motif de l'échec
     * (déjà calculé côté DAO via setDetailMessage) n'était jamais transmis au frontend, qui
     * affichait alors un message générique sans cause (cf. ListBacking.delete côté mic-jethro,
     * qui sait déjà extraire ce detail depuis une WebApplicationException).
     */
    @Operation(summary = "Supprime (soft-delete) un élément identifié par son uuid.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Suppression réussie"),
        @APIResponse(responseCode = "500", description = "Échec de la suppression : message détaillant la cause")
    })
    @DELETE
    @Path("{id}")
    @Transactional
    public Response supprimer(@Parameter(description = "uuid de l'élément à supprimer", required = true) @PathParam("id") String id){
        try {
            if (Boolean.TRUE.equals(service.supprimer(id))) {
                return Response.ok(true).build();
            }
        } catch (Exception e) {
            String detail = operationFeedback.getDetailMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(detail != null ? detail : e.getMessage())
                    .build();
        }
        String detail = operationFeedback.getDetailMessage();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(detail != null ? detail : ParametersConfig.FAILED_DELETE)
                .build();
    }

    public BasicCommonService<T> getService() {
        return service;
    }
}
