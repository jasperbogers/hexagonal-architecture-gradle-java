package com.jdriven.app;

import com.jdriven.adapters.inputhandler.FileInputHandler;
import com.jdriven.adapters.persistence.SystemOutPersistence;
import com.jdriven.domain.services.DomainDataInterpretationService;
import com.jdriven.domain.ports.DataInterpretationPort;
import com.jdriven.domain.ports.PersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application.
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Running main application.");

        /*
          Inject dependencies
         */
        PersistencePort persistencePort = new SystemOutPersistence();
        DataInterpretationPort dataInterpretationPort = new DomainDataInterpretationService(persistencePort);
        FileInputHandler fileInputHandler = new FileInputHandler(dataInterpretationPort);

        /*
          Initiate input
         */
        fileInputHandler.takeInput("data.csv");

        logger.info("Stopped running.");
    }
}
