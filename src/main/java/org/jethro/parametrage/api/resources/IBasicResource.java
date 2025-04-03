package org.jethro.parametrage.api.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import jakarta.ws.rs.core.Response;
import org.jethro.parametrage.api.services.BasicCommonService;

public interface IBasicResource<T> {
  @GET
  List<T> obtenirListe(@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
      , @HeaderParam("pageSize") @DefaultValue("0") int pageSize);

  @GET
  @Path("search")
  List<T> rechercher(
      @QueryParam("query") @DefaultValue("") String searchValue
      , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
      , @HeaderParam("pageSize") @DefaultValue("0") int pageSize);

  @GET
  @Path("{id}")
  T trouverParId(@PathParam("id") String id);

  @GET
  @Path("code/{code}")
  T trouverParCode(@PathParam("code") String code);

  @POST
  @Transactional
  Response ajouter(T t);

  @PUT
  @Path("{id}")
  @Transactional
  T modifier(@PathParam("id") String id, T t);

  @DELETE
  @Path("{id}")
  @Transactional
  Boolean supprimer(@PathParam("id") String id);

  BasicCommonService<T> getService();
}
