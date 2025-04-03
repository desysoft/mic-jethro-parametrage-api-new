package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.entities.Profession;

import jakarta.ws.rs.Path;

@Path("/v1/professions")
public class ProfessionResource extends BasicResource<Profession> {
}
