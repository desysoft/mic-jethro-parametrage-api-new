package org.jethro.parametrage.api.resources;

import org.jethro.parametrage.api.entities.Operateur;

import jakarta.ws.rs.Path;

@Path("v1/operateurs")
public class OperateurResource extends BasicResource_Hold<Operateur> {
}
