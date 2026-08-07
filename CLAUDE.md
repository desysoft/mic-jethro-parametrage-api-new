# CLAUDE.md — mic-jethro-parametrage-api

## 1. Contexte du projet

API de référentiels ("paramétrage") pour l'écosystème JETHRO : nomenclatures
géographiques, académiques, professionnelles et personnelles. Fournisseur
réel des données que `mic-members-managers-api` consomme en lecture seule
via son `ResourceClient`. Ce dépôt ne contient que du code applicatif : la
spécification complète (Spec-Kit) vit hors de ce dépôt, à lire **avant
toute tâche de développement** :

`/mnt/DATA/projects/java/JETHRO/specifications/specifications-mic-jethro-parametrage-api/`

Point d'entrée : `.spec-kit/README.md` de ce dossier.

Stack : Java 17, Quarkus 3.8.6.1, Hibernate ORM Panache, PostgreSQL
(schéma `parametrage` de la base `jethro_db`, partagée avec
`mic-members-managers-api`), Keycloak (OIDC), JAX-RS (RESTEasy),
OpenAPI/Swagger (`quarkus-smallrye-openapi`). Pas de cache à ce jour.

## 2. Règle d'or

**Ne jamais commit directement sur `develop` ou `main`.** Toute tâche de
`tasks.md` = une branche dédiée = un commit conventionnel référençant son
code de tâche `TCH_PAR_[SÉQ]` = un push. Détail complet du cycle :
`.spec-kit/constitution-git.md` (dossier de spécifications ci-dessus).

## 3. Où trouver les règles

Ne pas deviner une convention : elle est déjà écrite, dans le dossier de
spécifications externe.

| Question | Fichier |
| :--- | :--- |
| Vision, stack, principes d'architecture déjà en vigueur | `.spec-kit/constitution-global.md` |
| Règles de gestion (`RG_PAR_[ZONE]_[SÉQ]`) | `.spec-kit/constitution-rules.md` |
| Récits utilisateurs (`RU_PAR_[SÉQ]`) et critères d'acceptation | `.spec-kit/constitution-user-stories.md` |
| Scénarios métiers narratifs | `.spec-kit/constitution-scenarios.md` |
| Modèle conceptuel de données | `.spec-kit/constitution-datamodel.md` |
| Glossaire métier (FR) ↔ technique | `.spec-kit/constitution-lexicon.md` |
| Comment committer/pousser une tâche | `.spec-kit/constitution-git.md` |
| Backlog de tâches (`TCH_PAR_[SÉQ]`) | `.spec-kit/tasks.md` |

## 4. Ce que l'agent NE doit PAS faire

- Ne pas pousser vers `main`/`develop` sans demande explicite ou fusion
  explicite en fin de tâche (voir `constitution-git.md`).
- Ne pas modifier le périmètre d'une tâche sans le signaler dans le commit.
- Ne pas committer de secret (`.env`, mots de passe en dur dans
  `application.properties`, `run.sh`, `script/keycloack/jethro-apis.json`).
- Ne pas committer le dossier de spécifications sans demande explicite —
  ce sont des livrables d'analyse et de pilotage, pas du code applicatif.
- Ne pas démarrer une tâche dont les critères d'acceptation
  (`constitution-user-stories.md`) sont ambigus : demander une
  clarification plutôt que deviner.
- Ne pas casser le contrat REST existant : `mic-members-managers-api` est
  un consommateur connu de ce module, sans ticket de migration explicite.

## 5. Commandes utiles

| Action | Commande |
| :--- | :--- |
| Compiler (hors-ligne) | `mvn -o compile` |
| Lancer l'application en dev | `./run.sh` (positionne les variables d'environnement locales puis `quarkus:dev`) |
| Voir l'état Git | `git status` |
| Voir l'historique d'une branche vs `main` | `git log --oneline main..<branche>` |
