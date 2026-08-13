package org.jethro.parametrage.api.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.NeighborhoodCreateDto;
import org.jethro.parametrage.api.dto.NeighborhoodDto;
import org.jethro.parametrage.api.dto.NeighborhoodUpdateDto;
import org.jethro.parametrage.api.entities.Neighborhood;
import org.jethro.parametrage.api.services.NeighborhoodService;

import jakarta.ws.rs.*;
import java.util.List;


@Tag(name = "Quartiers", description = "Référentiel des quartiers, rattachés à une commune.")
@Path("/v1/neighborhoods")
public class NeighborhoodResource extends BasicResourceDto<Neighborhood, NeighborhoodDto> {


    NeighborhoodService neighborhoodService = null;

    @Operation(summary = "Liste les quartiers d'une commune")
    @GET
    @Path("commune/{id}")
    public List<Neighborhood> obtenirLesQuartiers(
            @Parameter(description = "uuid de la commune", required = true) @PathParam("id") String idCommune
            , @Parameter(description = "index de page, 0-based") @HeaderParam("pageIndex") @DefaultValue("0") int pageIndex
            , @Parameter(description = "taille de page, 0 = pas de pagination") @HeaderParam("pageSize") @DefaultValue("0") int pageSize){
        neighborhoodService = (NeighborhoodService) this.getService();
        return neighborhoodService.obtenirListeDesQuartiersParCommune(idCommune,pageIndex,pageSize);
    }


    @Operation(summary = "Crée un quartier à partir d'un NeighborhoodCreateDto (variante dédiée à la création, distincte du POST générique).")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Quartier créé"),
        @APIResponse(responseCode = "500", description = "Échec de la création : message détaillant la cause")
    })
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

    @Operation(summary = "Met à jour un quartier à partir d'un NeighborhoodUpdateDto (variante dédiée à la modification, distincte du PUT générique).")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Quartier modifié"),
        @APIResponse(responseCode = "500", description = "Échec de la modification : message détaillant la cause")
    })
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
