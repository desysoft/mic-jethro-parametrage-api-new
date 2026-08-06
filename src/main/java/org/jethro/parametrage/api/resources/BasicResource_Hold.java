package org.jethro.parametrage.api.resources;

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

    @GET
    public List<T> obtenirListe(@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            ,@HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.obtenirListe(pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }


    @GET
    @Path("search")
    public List<T> rechercher(
            @QueryParam("query") @DefaultValue("") String searchValue
            ,@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            ,@HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.rechercher(searchValue,pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @GET
    @Path("{id}")
    public T trouverParId(@PathParam("id") String id){
        return service.touverParId(id);
        //return this.trouverParId(id);
    }

    @GET
    @Path("code/{code}")
    public T trouverParCode(@PathParam("code") String code){
        return service.touverParCode(code);
    }

    @POST
    @Transactional
    public T ajouter(T t){
      try {
        return service.ajouter(t);
      } catch (Exception e) {
        throw new WebApplicationException(e);
      }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public T modifier(@PathParam("id") String id,T t){
        return service.modifer(id,t);
    }

    /**
     * Renvoie un statut 500 avec le detailMessage du DAO (via OperationFeedback) quand la
     * suppression échoue, au lieu d'un simple "false" : sans quoi le motif de l'échec
     * (déjà calculé côté DAO via setDetailMessage) n'était jamais transmis au frontend, qui
     * affichait alors un message générique sans cause (cf. ListBacking.delete côté mic-jethro,
     * qui sait déjà extraire ce detail depuis une WebApplicationException).
     */
    @DELETE
    @Path("{id}")
    @Transactional
    public Response supprimer(@PathParam("id") String id){
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
