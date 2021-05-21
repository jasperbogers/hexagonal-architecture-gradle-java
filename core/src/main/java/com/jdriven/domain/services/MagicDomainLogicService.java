package com.jdriven.domain.services;

import com.jdriven.domain.models.DomainData;
import com.jdriven.domain.ports.MagicDomainLogicPort;
import com.jdriven.domain.ports.PersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service that handles the magic business logic that is at the core of this application.
 */
public class MagicDomainLogicService implements MagicDomainLogicPort {

    private static final Logger logger = LoggerFactory.getLogger(MagicDomainLogicService.class);

    private final PersistencePort persistencePort;

    public MagicDomainLogicService(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    public void interpretData(DomainData inputData) {
        logger.info("Started interpreting data.");
        if (inputData.field2 == null) {
            // TODO Throwing an exception here will make the driving adapter responsible for handling it. That's not desirable.
            throw new IllegalArgumentException("Interpretation failed: field2 may not be null. Not persisting.");
        }
        logger.info("Finished interpreting data. Storing.");
        persistencePort.store(inputData);
        logger.info("Finished storing data.");
    }
}
