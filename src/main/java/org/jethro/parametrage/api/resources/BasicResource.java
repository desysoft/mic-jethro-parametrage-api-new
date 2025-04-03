package org.jethro.parametrage.api.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.core.Response;
import org.jethro.parametrage.api.services.BasicCommonService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResource<T> implements IBasicResource<T> {

    @Inject
    BasicCommonService<T> service;

    @Override
    @GET
    public List<T> obtenirListe(@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.obtenirListe(pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }


    @Override
    @GET
    @Path("search")
    public List<T> rechercher(
        @QueryParam("query") @DefaultValue("") String searchValue
        , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return service.rechercher(searchValue,pageIndex,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    @GET
    @Path("{id}")
    public T trouverParId(@PathParam("id") String id){
        return service.touverParId(id);
        //return this.trouverParId(id);
    }

    @Override
    @GET
    @Path("code/{code}")
    public T trouverParCode(@PathParam("code") String code){
        return service.touverParCode(code);
    }

    @Override
    @POST
    @Transactional
    public Response ajouter(T t) {
      try {
          service.ajouter(t);
          return Response.status(Response.Status.CREATED).entity(t).build();
      } catch (Exception e) {
          return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }
    }

    @Override
    @PUT
    @Path("{id}")
    @Transactional
    public T modifier(@PathParam("id") String id, T t) throws WebApplicationException{
        return service.modifer(id,t);
    }

    @Override
    @DELETE
    @Path("{id}")
    @Transactional
    public Boolean supprimer(@PathParam("id") String id){
        return service.supprimer(id);
    }

    @Override
    public BasicCommonService<T> getService() {
        return service;
    }
}
