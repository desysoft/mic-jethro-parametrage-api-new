package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.entities.Ville;

import jakarta.ws.rs.*;

@Path("/v1/villes")
public class VilleResource extends BasicResource_Hold<Ville> {
}
