package org.jethro.parametrage.api.coherence;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jethro.parametrage.api.dao.CardinaliteDao;
import org.jethro.parametrage.api.dao.ContrainteSexeDao;
import org.jethro.parametrage.api.dao.SensDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Compare l'ensemble des str_code de cardinalite/sens/contraintesexe à leur enum miroir
 * (entities.enums.*). Détection, pas prévention : les tables restent librement éditables
 * (même traitement que Sexe/CategorieProfession) — un écart est signalé, pas bloqué.
 * Cf. filiation-familiale.md § Tables de référence + enum miroir + vérification de cohérence.
 */
@ApplicationScoped
public class TypeFiliationCoherenceChecker {

    private static final Logger LOG = Logger.getLogger("TypeFiliationCoherenceChecker");

    @Inject
    CardinaliteDao cardinaliteDao;

    @Inject
    SensDao sensDao;

    @Inject
    ContrainteSexeDao contrainteSexeDao;

    void onStart(@Observes StartupEvent event) {
        CoherenceResult result = check();
        if (!result.ok) {
            for (CoherenceResult.TableDiscrepancy d : result.discrepancies) {
                LOG.severe("Écart table/enum détecté sur '" + d.table + "' — manquant en table : "
                        + d.missingInTable + ", manquant dans l'enum : " + d.missingInEnum);
            }
        }
    }

    public CoherenceResult check() {
        CoherenceResult result = new CoherenceResult();

        CoherenceResult.TableDiscrepancy cardinaliteDiscrepancy = compare(
                "cardinalite",
                cardinaliteDao.getList().stream().map(c -> c.code).collect(Collectors.toList()),
                enumCodes(org.jethro.parametrage.api.entities.enums.Cardinalite.values()));

        CoherenceResult.TableDiscrepancy sensDiscrepancy = compare(
                "sens",
                sensDao.getList().stream().map(s -> s.code).collect(Collectors.toList()),
                enumCodes(org.jethro.parametrage.api.entities.enums.Sens.values()));

        CoherenceResult.TableDiscrepancy contrainteSexeDiscrepancy = compare(
                "contraintesexe",
                contrainteSexeDao.getList().stream().map(c -> c.code).collect(Collectors.toList()),
                enumCodes(org.jethro.parametrage.api.entities.enums.ContrainteSexe.values()));

        List<CoherenceResult.TableDiscrepancy> all = Arrays.asList(cardinaliteDiscrepancy, sensDiscrepancy, contrainteSexeDiscrepancy);
        for (CoherenceResult.TableDiscrepancy d : all) {
            if (!d.isEmpty()) {
                result.discrepancies.add(d);
            }
        }
        result.ok = result.discrepancies.isEmpty();
        return result;
    }

    private static Set<String> enumCodes(Enum<?>[] values) {
        Set<String> codes = new HashSet<>();
        for (Enum<?> value : values) {
            codes.add(value.name());
        }
        return codes;
    }

    private static CoherenceResult.TableDiscrepancy compare(String table, List<String> tableCodes, Set<String> enumCodes) {
        Set<String> tableCodeSet = new HashSet<>();
        for (String code : tableCodes) {
            if (code != null) {
                tableCodeSet.add(code);
            }
        }

        List<String> missingInTable = new ArrayList<>();
        for (String enumCode : enumCodes) {
            if (!tableCodeSet.contains(enumCode)) {
                missingInTable.add(enumCode);
            }
        }

        List<String> missingInEnum = new ArrayList<>();
        for (String tableCode : tableCodeSet) {
            if (!enumCodes.contains(tableCode)) {
                missingInEnum.add(tableCode);
            }
        }

        return new CoherenceResult.TableDiscrepancy(table, missingInTable, missingInEnum);
    }
}
