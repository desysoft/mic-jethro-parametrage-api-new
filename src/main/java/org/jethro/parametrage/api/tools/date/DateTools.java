package org.jethro.parametrage.api.tools.date;


import org.jethro.parametrage.api.tools.log.MyLog;
import org.jethro.parametrage.api.tools.string.ToolString;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTools {

    static final Logger LOG = Logger.getLogger("DateTools");
    public static String pattern = "[dd/MM/yyyy] [HH:mm:ss]";

    public static LocalDateTime getDateNow(){
        return LocalDateTime.now();
    }

    public static String getStringDateNow(String format){
        try {
            if(format==null || format.equals("")){
                return LocalDateTime.now().toString();
            }
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        }catch (Exception e){
            new MyLog("DateTools", true).error(ToolString.getStringByArrayStackTraceElement(e));
        }
        return null;
    }

    /**
     * Vérifie si la date de début est inférieur à la date de fin
     *      Si la date de début est null, une valeur "false" est retournée
     *      Dès que la date de fin est null, une valeur "true" est retournée
     *
     * @param ldtStart
     * @param ldtEnd
     * @return boolean
     *
     */
    public static Boolean dateStartIsSmallThanDateEnd(LocalDateTime ldtStart, LocalDateTime ldtEnd){
        System.out.println("Date début ==="+ldtStart);
        System.out.println("Date fin ==="+ldtEnd);
        if(ldtStart==null) return false;
        if(ldtEnd==null) return true;
        return Duration.between(ldtEnd,ldtStart).isNegative();
    }


    public static LocalDateTime dateToLocalDateTime(Date date){
        return  LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate getLocalDateByString(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           return LocalDate.parse(date, formatter);
        }catch (Exception e){
            LOG.log(Level.INFO, "getLocalDateByString ++++ RECEIVED OBJECT : {0} "+ e.getMessage());
        }
        return null;
    }

    public static String getStringDateByLocalDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(date);
    }


}
