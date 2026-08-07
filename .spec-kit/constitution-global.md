# 🌍 Constitution : Vision & Architecture Globale — mic-jethro-parametrage-api

## 1. Vision

API de référentiels ("paramétrage") pour l'écosystème JETHRO : nomenclatures
géographiques (ville/commune/quartier), académiques (diplôme, filière,
niveau, formation), professionnelles (profession, type de travailleur) et
personnelles (sexe, tranche d'âge, situation matrimoniale, type de contact,
opérateur téléphonique, type d'intégration). C'est le fournisseur réel des
données que `mic-members-managers-api` consomme en lecture seule via son
`ResourceClient` — toute évolution de contrat ici a un impact direct côté
consommateur, même sans lien de compilation entre les deux dépôts.

## 2. Stack technique (état réel du dépôt)

| Couche | Techno |
| :--- | :--- |
| Langage / Runtime | Java 17, Quarkus 3.8.6.1 |
| Persistance | Hibernate ORM Panache, PostgreSQL — schéma `parametrage` de la base `jethro_db` (partagée avec `mic-members-managers-api`, schéma `members-management`) |
| Cache | **Absent** — aucune dépendance `quarkus-redis-cache`/`quarkus-cache` à ce jour |
| Auth | Keycloak (OIDC) |
| API | JAX-RS (RESTEasy), JSON (Jackson), OpenAPI/Swagger (`quarkus-smallrye-openapi`, exposé sur `/swagger-ui`), testable directement avec un token OIDC via le bouton Authorize (`SecurityScheme`, propagé à toutes les opérations par `OpenApiSecurityFilter`, TCH_PAR_006) |
| Racine des routes | `/jethro/api/` |

## 3. Principes directeurs (état après `improve-dao`/`improve-resource`/`cleanup`)

### 3.1 — Couche DAO générique (`CommonDao<T extends BaseEntity>`)

Toute entité hérite de `BaseEntity` (`uuid`, `code` unique, `status`,
horodatage/auteur création-modification — pas de `pkeyInstitutionId`,
ce module n'a pas de notion multi-institution). Tout DAO hérite de
`CommonDao<T>`, qui porte :
- `getList()` / `getList(pageIndex, pageSize)` / `getList(searchValue,...)` —
  filtrées sur `status = enable` ;
- `findByIdCustom(uuid)` / `findByCode(code)` — lecture unitaire ;
- `isExistCode(code)` / `codeAlreadyExists(code)` — contrôle d'unicité
  partagé, lève `CodeExistException` (package `exceptions`) en cas de
  doublon ;
- `softDelete(uuid)` — bascule `status = delete` puis persiste.

**Toute nouvelle entité de référentiel doit réutiliser ces méthodes plutôt
que de les réécrire.**

### 3.2 — Couche Resource générique (`BasicResourceDto<T,S>`)

CRUD REST générique (`GET /`, `GET /search`, `GET /{id}`, `GET /code/{code}`,
`POST /`, `PUT /{id}`, `DELETE /{id}`), hérité par toutes les Resources
concrètes — **c'est désormais la seule base Resource du dépôt** (voir
constat historique en §5). `obtenirListe`/`rechercher` enveloppent leur
corps dans `try/catch(Exception e){ e.printStackTrace(); return <valeur
par défaut> }` ; `ajouter` relance en `WebApplicationException` ;
`trouverParId`/`trouverParCode`/`modifier`/`supprimer` n'ont pas de
filet — écart connu, voir `constitution-rules.md` (RG_PAR_SYS_03).

### 3.3 — Mappers et prévention du N+1

Un mapper (`BaseMapper<T,S>`) ne doit jamais interroger un Service/DAO
dans une méthode `toDto()` appelée en boucle. Dès qu'une relation existe
(`Neighborhood → Commune`, `Formation → FormationType`), `toDtoList()`
doit être surchargé pour charger les entités liées **par lot** (une
requête pour N éléments) via une méthode `getByIds(List<String>)` du DAO
concerné, puis assemblées via `Map<uuid, dto>` — patron déjà appliqué sur
`NeighborhoodMapper`/`FormationMapper`, à reproduire pour toute nouvelle
relation.

### 3.4 — Exceptions métier

Package `exceptions`. Une `RuntimeException` simple par cas métier
(`CodeExistException`, `ObjectNotFoundException`), levée depuis le DAO au
plus près du contrôle qui échoue, jamais depuis le Service. Ne jamais
envelopper un `throw` d'exception métier dans un `try/catch(Exception e)`
qui le re-wrapperait en `RuntimeException` générique — ça fait perdre le
type au premier appelant qui voudrait le distinguer (bug corrigé sur
`Filiere`/`Profession`/`Neighborhood` dans la branche `improve-dao`, voir
`tasks.md` TCH_PAR_001).

### 3.5 — Un seul type de DTO par concept

Chaque entité expose un DTO dédié (`XxxDto extends BaseEntityDTO`,
Lombok `@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)`), jamais l'entité JPA directement.
`toEntity()` lève `UnsupportedOperationException` s'il n'a aucun appelant
réel (cas où la création/modification passe par des DTO dédiés
`*CreateDto`/`*UpdateDto`, comme `Neighborhood`) plutôt que de renvoyer
`null` silencieusement.

## 4. Contrainte transverse : contrat API stable

`mic-members-managers-api` est un consommateur connu de ce module (via
`ResourceClient`, en lecture). **Toute évolution de endpoint, DTO ou code
de statut doit être un ajout, jamais une rupture**, sauf ticket explicite
de migration concertée entre les deux dépôts.

## 5. Constat historique : 3 bases Resource concurrentes (résolu)

Avant la branche `improve-resource`, trois bases coexistaient
(`BasicResource_Hold`, `BasicResource`, `BasicResourceDto`), et 16 des 17
entités "réelles" exposaient leur entité JPA brute au lieu d'un DTO — seule
`Neighborhood` suivait le patron cible. Résolu : toutes les Resources
héritent maintenant de `BasicResourceDto`, les deux autres bases et
l'interface `IBasicResource` associée ont été supprimées.

## 6. Ce que ce Spec-Kit ne couvre pas (hors périmètre)

- `mic-members-managers-api` — dépôt séparé, documenté dans son propre
  `.spec-kit/`.
- Le module d'authentification/Keycloak — configuré, pas développé ici.
- `MatrimonialInfos`, `SituationAcademique`, `SituationProfessionnelle` —
  entités JPA présentes mais sans aucune couche DAO/Service/Resource/Mapper
  (voir RG_PAR_SYS_04, constat).
