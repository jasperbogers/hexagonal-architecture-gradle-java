package com.jdriven.application;

import com.jdriven.adapters.inputhandler.FileInputHandler;
import com.jdriven.adapters.persistence.SystemOutPersistence;
import com.jdriven.domain.services.MagicDomainLogicService;
import com.jdriven.domain.ports.MagicDomainLogicPort;
import com.jdriven.domain.ports.PersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application.
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        logger.info("Running main application.");

        /*
          Inject dependencies
         */
        PersistencePort persistencePort = new SystemOutPersistence();
        MagicDomainLogicPort magicDomainLogicPort = new MagicDomainLogicService(persistencePort);
        FileInputHandler fileInputHandler = new FileInputHandler(magicDomainLogicPort);

        /*
          Initiate input
         */
        fileInputHandler.takeInput("data.csv");

        logger.info("Stopped running.");
    }
}
