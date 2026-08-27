package org.jethro.parametrage.api.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jethro.parametrage.api.dao.OperationFeedback;
import org.jethro.parametrage.api.services.BasicCommonService;
import org.jethro.parametrage.api.tools.ParametersConfig;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResource<T> implements IBasicResource<T> {

    @Inject
    BasicCommonService<T> service;

    @Inject
    OperationFeedback operationFeedback;

    @Operation(summary = "Liste paginée. pageSize=0 renvoie l'intégralité sans pagination.")
    @Override
    @GET
    public List<T> obtenirListe(
        @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.obtenirListe(pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }


    @Operation(summary = "Recherche paginée par texte libre (paramètre query). pageSize=0 renvoie l'intégralité des résultats sans pagination.")
    @Override
    @GET
    @Path("search")
    public List<T> rechercher(
        @Parameter(description = "texte recherché") @QueryParam("query") @DefaultValue("") String searchValue
        , @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.rechercher(searchValue,pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Operation(summary = "Recherche un élément par son identifiant technique (uuid).")
    @Override
    @GET
    @Path("{id}")
    public T trouverParId(@Parameter(description = "uuid de l'élément", required = true) @PathParam("id") String id){
        return service.touverParId(id);
        //return this.trouverParId(id);
    }

    @Operation(summary = "Recherche un élément par son code métier unique.")
    @Override
    @GET
    @Path("code/{code}")
    public T trouverParCode(@Parameter(description = "code métier de l'élément", required = true) @PathParam("code") String code){
        return service.touverParCode(code);
    }

    /**
     * Les DaoImpl.save() rejettent une création invalide (code déjà existant, etc.) en
     * renvoyant null après avoir positionné operationFeedback — sans lever d'exception.
     * L'ancien code ignorait ce retour et répondait toujours 201 avec le DTO soumis par le
     * client, même quand la persistance avait échoué. cf. supprimer() ci-dessous, même
     * correctif.
     */
    @Operation(summary = "Crée un nouvel élément.")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Élément créé"),
        @APIResponse(responseCode = "500", description = "Échec de la création : message détaillant la cause (ex. code déjà existant)")
    })
    @Override
    @POST
    @Transactional
    public Response ajouter(T t) {
      try {
          T result = service.ajouter(t);
          if (result == null) {
              String detail = operationFeedback.getDetailMessage();
              return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                      .entity(detail != null ? detail : ParametersConfig.FAILED_CREATE)
                      .build();
          }
          return Response.status(Response.Status.CREATED).entity(result).build();
      } catch (Exception e) {
          return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }
    }

    @Operation(summary = "Met à jour un élément existant.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Élément modifié"),
        @APIResponse(responseCode = "500", description = "Échec de la modification, détail du motif dans le corps de la réponse")
    })
    @Override
    @PUT
    @Path("{id}")
    @Transactional
    public T modifier(@Parameter(description = "uuid de l'élément à modifier", required = true) @PathParam("id") String id, T t) throws WebApplicationException{
        T result = service.modifer(id,t);
        if (result == null) {
            String detail = operationFeedback.getDetailMessage();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(detail != null ? detail : ParametersConfig.FAILED_UPDATE)
                    .build());
        }
        return result;
    }

    /**
     * cf. BasicResource_Hold.supprimer (même correctif) : renvoie le detailMessage du DAO
     * en 500 plutôt qu'un simple "false", pour que le motif de l'échec remonte jusqu'au
     * frontend au lieu d'un message générique sans cause.
     */
    @Operation(summary = "Supprime (soft-delete) un élément identifié par son uuid.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Suppression réussie"),
        @APIResponse(responseCode = "500", description = "Échec de la suppression : message détaillant la cause")
    })
    @Override
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

    @Override
    public BasicCommonService<T> getService() {
        return service;
    }
}
