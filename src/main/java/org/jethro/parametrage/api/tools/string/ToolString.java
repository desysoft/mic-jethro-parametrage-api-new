package org.jethro.parametrage.api.tools.string;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class ToolString {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    public static String getStringByArrayStackTraceElement(Exception e){
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for(StackTraceElement ste : e.getStackTrace()){
            sb.append(ste.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getComplexId(String prefixe) {
        // Obtenir la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();

        // Formater la date/heure comme yyyyMMddHHmmss
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));

        // Obtenir les millisecondes actuelles (convertir les nanosecondes)
        int milliseconds = now.getNano() / 1_000_000;

        // Obtenir une valeur numérique supplémentaire basée sur le compteur unique
        long counterValue = COUNTER.incrementAndGet() % 1000;  // Limite à 3 chiffres (0 - 999) à cause du modulo 1000

        // Générer un nombre aléatoire (100 à 999)
        int randomNumber = ThreadLocalRandom.current().nextInt(100, 1000);

        // Construire l'identifiant final
        String catime = String.format("%s%03d%03d%03d", formattedDateTime, milliseconds, counterValue, randomNumber);

        // Limiter la chaîne à 20 caractères
        if (catime.length() > 20) {
            catime = catime.substring(0, 20);
        }
        return prefixe+"_"+catime;
    }
}
