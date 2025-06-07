package utils;

import java.util.logging.Logger;

public class GlobalLogger {
    private static final Logger logger = Logger.getLogger("RPGLogger");

    public static Logger getLogger() {
        return logger;
    }
}
