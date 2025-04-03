package org.jethro.parametrage.api.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;
import org.jethro.parametrage.api.services.NeighborhoodService;

import jakarta.ws.rs.*;
import java.util.List;


@Path("/v1/neighborhoods")
public class NeighborhoodResource extends BasicResourceDto<Neighborhood, NeighborhoodDto> {


    NeighborhoodService neighborhoodService = null;

    @GET
    @Path("commune/{id}")
    public List<Neighborhood> obtenirLesQuartiers(@PathParam("id") String idCommune
            , @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        neighborhoodService = (NeighborhoodService) this.getService();
        return neighborhoodService.obtenirListeDesQuartiersParCommune(idCommune,pageIndex,pageSize);
    }


    @POST
    @Path("by-dto")
    @Transactional
    public Response createByDto(NeighborhoodCreateDto neighborhoodCreateDTO) {
        try {
            neighborhoodService = (NeighborhoodService) this.getService();
            Neighborhood neighborhood = neighborhoodService.ajouterParNeighborhoodCreateDto(neighborhoodCreateDTO);
            return Response.status(Response.Status.CREATED).entity(neighborhood).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("by-dto")
    @Transactional
    public Response updateByDto(NeighborhoodUpdateDto neighborhoodUpdateDto) {
        try {
            neighborhoodService = (NeighborhoodService) this.getService();
            Neighborhood neighborhood = neighborhoodService.modifierParNeighborhoodUpdateDto(neighborhoodUpdateDto);
            System.out.println("NeighborhoodResource updateByDto ++++ neighborhoodUpdateDto == "+neighborhoodUpdateDto);
            return Response.status(Response.Status.OK).entity(neighborhood).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }



}
