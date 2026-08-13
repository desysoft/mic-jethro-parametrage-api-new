package org.jethro.parametrage.api.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jethro.parametrage.api.dao.OperationFeedback;
import org.jethro.parametrage.api.mapper.BaseMapper;
import org.jethro.parametrage.api.services.BasicCommonService;
import org.jethro.parametrage.api.tools.ParametersConfig;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResourceDto<T,S> implements IBasicResourceDto<T,S> {

    @Inject
    BasicCommonService<T> service;

    @Inject
    BaseMapper<T,S> mapper;

    @Inject
    OperationFeedback operationFeedback;

    @Operation(summary = "Liste paginée. pageSize=0 renvoie l'intégralité sans pagination.")
    @Override
    @GET
    public List<S> obtenirListe(
        @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            /*return service.obtenirListe(pageIndex,pageSize)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());*/
            return mapper.toDtoList(service.obtenirListe(pageIndex,pageSize));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<S>();
    }


    @Operation(summary = "Recherche paginée par texte libre (paramètre query). pageSize=0 renvoie l'intégralité des résultats sans pagination.")
    @Override
    @GET
    @Path("search")
    public List<S> rechercher(
        @Parameter(description = "texte recherché") @QueryParam("query") @DefaultValue("") String searchValue
        , @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.rechercher(searchValue,pageIndex,pageSize)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Operation(summary = "Recherche un élément par son identifiant technique (uuid).")
    @Override
    @GET
    @Path("{id}")
    public S trouverParId(@Parameter(description = "uuid de l'élément", required = true) @PathParam("id") String id){
        return mapper.toDto(service.touverParId(id));
        //return this.trouverParId(id);
    }

    @Operation(summary = "Recherche un élément par son code métier unique.")
    @Override
    @GET
    @Path("code/{code}")
    public S trouverParCode(@Parameter(description = "code métier de l'élément", required = true) @PathParam("code") String code){
        return mapper.toDto(service.touverParCode(code));
    }

    @Operation(summary = "Crée un nouvel élément.")
    @Override
    @POST
    @Transactional
    public S ajouter(T t){
      try {
        return mapper.toDto(service.ajouter(t));
      } catch (Exception e) {
            throw new WebApplicationException(e);
      }
    }

    @Operation(summary = "Met à jour un élément existant.")
    @Override
    @PUT
    @Path("{id}")
    @Transactional
    public S modifier(@Parameter(description = "uuid de l'élément à modifier", required = true) @PathParam("id") String id, T t){
        return mapper.toDto(service.modifer(id,t));
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

    @Override
    public BaseMapper<T, S> getMapper() {
        return mapper;
    }

}
