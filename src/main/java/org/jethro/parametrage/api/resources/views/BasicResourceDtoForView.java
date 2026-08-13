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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jethro.parametrage.api.mapper.BaseViewMapper;
import org.jethro.parametrage.api.services.views.BasicCommonServiceForView;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResourceDtoForView<T,S> implements IBasicResourceForViewDto<T,S> {

    @Inject
    BasicCommonServiceForView<T> service;

    @Inject
    BaseViewMapper<T,S> mapper;

    @Operation(summary = "Liste paginée (vue en lecture). pageSize=0 renvoie l'intégralité sans pagination.")
    @Override
    @GET
    public List<S> obtenirListe(
        @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
        , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        try {
            return mapper.dtoByViewList(service.obtenirListe(pageIndex,pageSize));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<S>();
    }


    @Operation(summary = "Recherche paginée par texte libre (paramètre query), sur la vue en lecture. pageSize=0 renvoie l'intégralité des résultats sans pagination.")
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
                .map(mapper::dtoByView)
                .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Operation(summary = "Recherche un élément par son identifiant technique (uuid), sur la vue en lecture.")
    @Override
    @GET
    @Path("{id}")
    public S trouverParId(@Parameter(description = "uuid de l'élément", required = true) @PathParam("id") String id){
        return mapper.dtoByView(service.touverParId(id));
        //return this.trouverParId(id);
    }

    @Operation(summary = "Recherche un élément par son code métier unique, sur la vue en lecture.")
    @Override
    @GET
    @Path("code/{code}")
    public S trouverParCode(@Parameter(description = "code métier de l'élément", required = true) @PathParam("code") String code){
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
