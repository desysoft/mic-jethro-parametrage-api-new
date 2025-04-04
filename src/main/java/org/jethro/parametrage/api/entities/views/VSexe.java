package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(VSexe.QUERY)
public class VSexe extends BaseEntityForParameterView {

  public static final String QUERY = """
      SELECT
            t.id,
            t.str_code AS code,
            t.str_name AS libelle,
            COUNT(p.id) AS total_personnes
      FROM parametrage.sexe t
      LEFT JOIN "members-management".person p ON t.id = p.pkey_sexe_id
      WHERE t.status = 'enable'
      GROUP BY t.id, t.str_code, t.str_name
      ORDER BY t.str_code, t.str_name
    """;
}
