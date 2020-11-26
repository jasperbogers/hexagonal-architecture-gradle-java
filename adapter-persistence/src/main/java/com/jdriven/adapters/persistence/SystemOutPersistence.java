package com.jdriven.adapters.persistence;

import com.jdriven.domain.models.DomainData;
import com.jdriven.domain.ports.PersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the PersistencePort based on System.out
 */
public class SystemOutPersistence implements PersistencePort {
    private static final Logger logger = LoggerFactory.getLogger(SystemOutPersistence.class);

    public void store(DomainData domainData) {
        logger.info("Persisted domainData: {}, {}", domainData.field1, domainData.field2);
    }
}
