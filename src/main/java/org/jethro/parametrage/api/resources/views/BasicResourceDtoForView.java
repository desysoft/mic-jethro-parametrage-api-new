package org.jethro.parametrage.api.resources.views;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jethro.parametrage.api.mapper.BaseViewMapper;
import org.jethro.parametrage.api.services.views.BasicCommonServiceForView;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResourceDtoForView<T,S> implements IBasicResourceForViewDto<T,S> {

    @Inject
    BasicCommonServiceForView<T> service;

    @Inject
    BaseViewMapper<T,S> mapper;

    @Override
    @GET
    public List<S> obtenirListe(@HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return mapper.dtoByViewList(service.obtenirListe(pageIndex,pageSize));
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
                .map(mapper::dtoByView)
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
        return mapper.dtoByView(service.touverParId(id));
        //return this.trouverParId(id);
    }

    @Override
    @GET
    @Path("code/{code}")
    public S trouverParCode(@PathParam("code") String code){
        return mapper.dtoByView(service.touverParCode(code));
    }


    @Override
    public BasicCommonServiceForView<T> getService() {
        return service;
    }

    @Override
    public BaseViewMapper<T, S> getMapper() {
        return mapper;
    }

}
