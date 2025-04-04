package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(VFiliere.QUERY)
public class VFiliere extends BaseEntityForParameterView {

  public static final String QUERY = """
    SELECT
         t.id, t.str_code AS code, t.str_name AS libelle,
         COUNT(*) FILTER (WHERE s.str_code = 'M') AS nombre_hommes,
         COUNT(*) FILTER (WHERE s.str_code = 'F') AS nombre_femmes,
         COUNT(p.id) AS total_personnes
         FROM "members-management".person p
         JOIN parametrage.filiere t ON t.id = p.pkey_filiere_id
         JOIN parametrage.sexe s ON s.id = p.pkey_sexe_id
         GROUP BY t.id,t.str_code, t.str_name
         ORDER BY t.id,t.str_code, t.str_name
      """;
}
