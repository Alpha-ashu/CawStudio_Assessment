package utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {
    private static Logger logger = Logger.getLogger(Log4j.class);

    public static void configure() {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.error(message);
    }
}
