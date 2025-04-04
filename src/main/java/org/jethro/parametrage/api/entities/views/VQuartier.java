package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(VQuartier.QUERY)
public class VQuartier extends BaseEntityForParameterView {


  @Column(name = "id_commune")
  public String idCommune;

  @Column(name = "code_commune")
  public String codeCommune;

  @Column(name = "libelle_commune")
  public String libelleCommune;

  public static final String QUERY = """
    SELECT
         q.id, q.str_code AS code, q.str_name AS libelle,c.id AS id_commune,c.str_code AS code_commune, c.str_name AS libelle_commune,
         COUNT(p.id) FILTER (WHERE s.str_code = 'M') AS nombre_hommes,
         COUNT(p.id) FILTER (WHERE s.str_code = 'F') AS nombre_femmes,
         COUNT(p.id) AS total_personnes
         FROM parametrage.quartier q
         LEFT JOIN parametrage.commune c ON q.id_commune = c.id
         LEFT JOIN "members-management".person p ON q.id = p.pkey_quartier_id
         LEFT JOIN parametrage.sexe s ON s.id = p.pkey_sexe_id
         WHERE q.status = 'enable'
         GROUP BY c.id, c.str_name,c.str_code,q.id,q.str_code, q.str_name
         ORDER BY c.str_code,c.str_name,q.id,q.str_code, q.str_name
      """;
}
