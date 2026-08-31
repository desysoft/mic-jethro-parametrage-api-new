package org.jethro.parametrage.api.coherence;

import java.util.ArrayList;
import java.util.List;

/**
 * Réponse de GET v1/typefiliation-config/coherence — { "ok": true } ou
 * { "ok": false, "discrepancies": [...] }, cf. filiation-familiale.md § Vérification de
 * cohérence table ↔ enum miroir.
 */
public class CoherenceResult {

    public boolean ok;
    public List<TableDiscrepancy> discrepancies = new ArrayList<>();

    public static class TableDiscrepancy {
        public String table;
        public List<String> missingInTable = new ArrayList<>();
        public List<String> missingInEnum = new ArrayList<>();

        public TableDiscrepancy(String table, List<String> missingInTable, List<String> missingInEnum) {
            this.table = table;
            this.missingInTable = missingInTable;
            this.missingInEnum = missingInEnum;
        }

        public boolean isEmpty() {
            return missingInTable.isEmpty() && missingInEnum.isEmpty();
        }
    }
}
