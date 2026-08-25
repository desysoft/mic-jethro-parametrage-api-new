package org.jethro.parametrage.api.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.dto.ProfessionCreateDto;
import org.jethro.parametrage.api.dto.ProfessionUpdateDto;
import org.jethro.parametrage.api.entities.Profession;
import org.jethro.parametrage.api.services.ProfessionService;

import jakarta.ws.rs.*;

@Tag(name = "Professions", description = "Référentiel des professions.")
@Path("/v1/professions")
public class ProfessionResource extends BasicResource<Profession> {

    ProfessionService professionService = null;

    @Operation(summary = "Crée une profession à partir d'un ProfessionCreateDto (variante dédiée à la création, distincte du POST générique).")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Profession créée"),
        @APIResponse(responseCode = "500", description = "Échec de la création : message détaillant la cause")
    })
    @POST
    @Path("by-dto")
    @Transactional
    public Response createByDto(ProfessionCreateDto professionCreateDto) {
        try {
            professionService = (ProfessionService) this.getService();
            Profession profession = professionService.ajouterParProfessionCreateDto(professionCreateDto);
            return Response.status(Response.Status.CREATED).entity(profession).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Operation(summary = "Met à jour une profession à partir d'un ProfessionUpdateDto (variante dédiée à la modification, distincte du PUT générique).")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Profession modifiée"),
        @APIResponse(responseCode = "500", description = "Échec de la modification : message détaillant la cause")
    })
    @PUT
    @Path("by-dto")
    @Transactional
    public Response updateByDto(ProfessionUpdateDto professionUpdateDto) {
        try {
            professionService = (ProfessionService) this.getService();
            Profession profession = professionService.modifierParProfessionUpdateDto(professionUpdateDto);
            return Response.status(Response.Status.OK).entity(profession).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
