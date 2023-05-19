package ch.hearc.mnemosyne.mnemosyne_logger.service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ch.hearc.mnemosyne.mnemosyne_logger.model.SimpleLog;

public class LoggerService {
    public static void log(SimpleLog log) throws IOException {
        String fileName= log.getType().getType().toLowerCase() + ".log";

        FileHandler fileHandler = new FileHandler(fileName, true);

        fileHandler.setFormatter(new SimpleFormatter());

        Logger logger = Logger.getLogger(fileName);

        logger.addHandler(fileHandler);

        logger.info(log.toString());
    }
}
