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

    @Override
    @GET
    public List<S> obtenirListe(@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
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


    @Override
    @GET
    @Path("search")
    public List<S> rechercher(
        @QueryParam("query") @DefaultValue("") String searchValue
        , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
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

    @Override
    @GET
    @Path("{id}")
    public S trouverParId(@PathParam("id") String id){
        return mapper.toDto(service.touverParId(id));
        //return this.trouverParId(id);
    }

    @Override
    @GET
    @Path("code/{code}")
    public S trouverParCode(@PathParam("code") String code){
        return mapper.toDto(service.touverParCode(code));
    }

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

    @Override
    @PUT
    @Path("{id}")
    @Transactional
    public S modifier(@PathParam("id") String id, T t){
        return mapper.toDto(service.modifer(id,t));
    }

    /**
     * cf. BasicResource_Hold.supprimer (même correctif) : renvoie le detailMessage du DAO
     * en 500 plutôt qu'un simple "false", pour que le motif de l'échec remonte jusqu'au
     * frontend au lieu d'un message générique sans cause.
     */
    @Override
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

    @Override
    public BasicCommonService<T> getService() {
        return service;
    }

    @Override
    public BaseMapper<T, S> getMapper() {
        return mapper;
    }

}
