package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.entities.FormationType;

import jakarta.ws.rs.Path;

@Path("/v1/formationtypes")
public class FormationTypeResource extends BasicResource_Hold<FormationType> {
}
