# ✅ Tâches — mic-jethro-parametrage-api

Traduit la Constitution en unités de travail concrètes, assignables à un
agent IA en autonomie via `constitution-git.md`. Codification `TCH_PAR_
[SÉQ]`, préfixée par zone : `0xx` SYS (transverse), `1xx` GEO, `2xx` PER,
`3xx` ACA, `4xx` PRO, `5xx` INT, `9xx` hors périmètre des 6 zones
(fonctionnalité neuve, pas une correction).

Ce fichier n'entre pas dans une éventuelle fusion des `constitution-*.md` —
document vivant de suivi.

## Légende de statut

| Statut | Signification |
| :--- | :--- |
| ☐ | À faire |
| 🔄 | En cours |
| ✅ | Terminé |
| ⛔ | Bloqué |

---

## Matrice de couverture (Récits Utilisateurs → Tâches)

| Récit Utilisateur | Tâches couvrantes |
| :--- | :--- |
| RU_PAR_001 — CRUD générique | TCH_PAR_001 ✅, TCH_PAR_002 ✅ |
| RU_PAR_002 — Hiérarchie géographique | TCH_PAR_002 ✅ (N+1 Neighborhood), TCH_PAR_101 |
| RU_PAR_003 — Formation ↔ Type | TCH_PAR_002 ✅ (N+1 Formation) |
| RU_PAR_004 — Consommation externe | Socle existant (`pageSize=0`), pas de tâche dédiée |

---

## Zone SYS — Plateforme transverse

| Code | Statut | Tâche | RG/RU liées | Référence |
| :--- | :---: | :--- | :--- | :--- |
| TCH_PAR_001 | ✅ | Corriger la régression hard-delete (Filiere/Profession/Neighborhood), généraliser `CodeExistException` sur les 17 DAO non-vue, retirer les `try/catch` qui masquaient son type | RG_PAR_SYS_01, RG_PAR_SYS_02 | branche `improve-dao` |
| TCH_PAR_002 | ✅ | Consolider les 3 bases Resource en une seule (`BasicResourceDto`), créer/implémenter DTO+Mapper pour les 16 entités qui en manquaient ou avaient un stub, corriger le N+1 `NeighborhoodMapper`/`FormationMapper` | RG_PAR_SYS_05, RU_PAR_001, RU_PAR_002, RU_PAR_003 | branche `improve-resource` |
| TCH_PAR_003 | ✅ | Retirer code mort, `System.out.println` de debug, constantes hors-domaine de `ParametersConfig` | — | branche `cleanup` |
| TCH_PAR_004 | ☐ | Ajouter le filet `try/catch` manquant sur `trouverParId`/`trouverParCode`/`modifier`/`supprimer` de `BasicResourceDto` (même geste que `mic-members-managers-api`, `ameliorations/resource/01-gestion-erreurs-globale.md`) | RG_PAR_SYS_03 | — |
| TCH_PAR_005 | ☐ | Évaluer et éventuellement poser un cache Redis cache-aside sur les référentiels les plus lus par `mic-members-managers-api` (patron déjà documenté dans `quarkus-panache-spec-kit/.spec-kit/constitution-server-impl-quarkus.md` §3) | RG_PAR_SYS_06 | — |
| TCH_PAR_006 | ✅ | Exposer le token OIDC via le bouton Authorize de Swagger UI (schéma `SecurityScheme` appliqué à toutes les opérations via un `OASFilter`), désactivable sans toucher au code via la propriété `openapi.security.enabled` | — | commit `6900be8` |

## Zone GEO — Géographie

| Code | Statut | Tâche | RG/RU liées |
| :--- | :---: | :--- | :--- |
| TCH_PAR_101 | ☐ | Arbitrer et, si retenu, exposer le champ `description` de `Neighborhood` dans `NeighborhoodDto`/`NeighborhoodMapper` | RG_PAR_GEO_02, RU_PAR_002 |

## Zone 9xx — Hors périmètre des 6 zones (fonctionnalité neuve)

| Code | Statut | Tâche | RG/RU liées |
| :--- | :---: | :--- | :--- |
| TCH_PAR_901 | ☐ | Construire la couche CRUD complète (DAO/Service/Resource/Mapper/DTO) pour `MatrimonialInfos`, `SituationAcademique`, `SituationProfessionnelle` — actuellement de simples `@Entity` inaccessibles via l'API | RG_PAR_SYS_04 |

---

## Notes de suivi

* Les tâches ✅ sont vérifiables sur les branches `improve-dao`/
  `improve-resource`/`cleanup` de ce dépôt (non encore fusionnées sur
  `main` au 2026-08-05).
* TCH_PAR_901 est volontairement isolé en zone 9xx : contrairement aux
  autres tâches de ce backlog, ce n'est pas une correction d'un
  comportement existant mais une fonctionnalité neuve à spécifier
  (`RU_PAR_*` dédiés) avant implémentation.
* Mettre à jour la colonne **Statut** au fil de l'avancement (☐ → 🔄 → ✅,
  ou ⛔ avec la raison ajoutée en note).
