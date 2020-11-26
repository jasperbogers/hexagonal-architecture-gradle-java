package com.jdriven.domain.ports;

import com.jdriven.domain.models.DomainData;

/**
 * "Driven" Adapter
 * d nieu
 * Port for persistence of core data.
 */
public interface PersistencePort {
    void store(DomainData domainData);
}
