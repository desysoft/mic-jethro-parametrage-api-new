package org.jethro.parametrage.api.entities.enums;

/**
 * Miroir de la table {@code parametrage.cardinalite} (entities.Cardinalite). Utilisé par tout
 * code métier qui a besoin d'un switch exhaustif compilé — la table reste la source éditable,
 * cf. coherence.TypeFiliationCoherenceChecker pour la détection d'écart.
 */
public enum Cardinalite {
    UNIQUE,
    MULTIPLE
}
