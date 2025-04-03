package org.jethro.parametrage.api.tools.log;


import org.jethro.parametrage.api.tools.ParametersConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLog {

    private Logger logger;
    private Boolean inlogFile;

    static String format = "%d{HH:mm:ss} %-5p [%c{2.}] (%t) %s%e%n";



    public MyLog(String className, Boolean inlogFile) {
        this.logger = Logger.getLogger(className);
        this.inlogFile = inlogFile;
        if(this.inlogFile==true){
            try {
                Handler fh = new FileHandler(ParametersConfig.path_log_file+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+".log");
                this.logger.addHandler(fh);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void info(Object object){
        try {
            this.logger.info(object.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void error(Object object){
        try {
            this.logger.setLevel(Level.SEVERE);
            this.logger.info(object.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
