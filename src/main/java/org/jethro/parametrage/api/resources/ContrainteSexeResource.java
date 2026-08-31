package org.jethro.parametrage.api.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jethro.parametrage.api.entities.ContrainteSexe;

import jakarta.ws.rs.Path;

@Tag(name = "Contraintes de sexe", description = "Référentiel des contraintes de sexe de type de filiation (Aucune, Personne liée masculin/féminin, Sexes différents).")
@Path("v1/contraintesexe")
public class ContrainteSexeResource extends BasicResource<ContrainteSexe> {
}
