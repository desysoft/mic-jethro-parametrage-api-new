package org.jethro.parametrage.api.entities.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(VProfession.QUERY)
public class VProfession extends BaseEntityForParameterView {

  @Column(name = "id_categorie_profession")
  public String idCategorieProfession;

  @Column(name = "code_categorie_profession")
  public String codeCategorieProfession;

  @Column(name = "libelle_categorie_profession")
  public String libelleCategorieProfession;

  public static final String QUERY = """
      SELECT
          t.id,
          t.str_code AS code,
          t.str_name AS libelle,
          cp.id AS id_categorie_profession,
          cp.str_code AS code_categorie_profession,
          cp.str_name AS libelle_categorie_profession,
          COUNT(p.id) FILTER (WHERE s.str_code = 'M') AS nombre_hommes,
          COUNT(p.id) FILTER (WHERE s.str_code = 'F') AS nombre_femmes,
          COUNT(p.id) AS total_personnes
          FROM parametrage.job t
          LEFT JOIN parametrage.categorie_profession cp ON t.id_categorie_profession = cp.id
          LEFT JOIN "members-management".person p ON t.id = p.pkey_professional_id
          LEFT JOIN parametrage.sexe s ON s.id = p.pkey_sexe_id
          WHERE t.status = 'enable'
          GROUP BY t.id, t.str_code, t.str_name, cp.id, cp.str_code, cp.str_name
          ORDER BY total_personnes DESC , t.str_code, t.str_name
      """;
}
