package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.services.BasicCommonService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResource_Hold<T> {

    @Inject
    BasicCommonService<T> service;

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

    @DELETE
    @Path("{id}")
    @Transactional
    public Boolean supprimer(@PathParam("id") String id){
        return service.supprimer(id);
    }

    public BasicCommonService<T> getService() {
        return service;
    }
}
