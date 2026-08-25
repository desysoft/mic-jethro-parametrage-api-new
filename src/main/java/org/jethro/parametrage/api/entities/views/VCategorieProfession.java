package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(VCategorieProfession.QUERY)
public class VCategorieProfession extends BaseEntityForParameterView {

  @Column(name = "description")
  public String description;

  public static final String QUERY = """
      SELECT
          t.id,
          t.str_code AS code,
          t.str_name AS libelle,
          t.str_description AS description,
          COUNT(p.id) FILTER (WHERE s.str_code = 'M') AS nombre_hommes,
          COUNT(p.id) FILTER (WHERE s.str_code = 'F') AS nombre_femmes,
          COUNT(p.id) AS total_personnes
          FROM parametrage.categorie_profession t
          LEFT JOIN parametrage.job j ON j.id_categorie_profession = t.id
          LEFT JOIN "members-management".person p ON j.id = p.pkey_professional_id
          LEFT JOIN parametrage.sexe s ON s.id = p.pkey_sexe_id
          WHERE t.status = 'enable'
          GROUP BY t.id, t.str_code, t.str_name, t.str_description
          ORDER BY total_personnes DESC , t.str_code, t.str_name
      """;
}
