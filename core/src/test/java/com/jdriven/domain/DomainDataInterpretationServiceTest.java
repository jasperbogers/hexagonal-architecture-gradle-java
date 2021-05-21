package com.jdriven.domain;

import com.jdriven.domain.models.DomainData;
import com.jdriven.domain.ports.PersistencePort;
import com.jdriven.domain.services.MagicDomainLogicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the data interpreter.
 */
public class DomainDataInterpretationServiceTest {
    private PersistencePort persistencePort;

    /**
     * Mock implementation of PersistencePort
     */
    private static class MockPersistencePort implements PersistencePort {
        public void store(DomainData domainData) {
            // Mock. Do nothing.
        }
    }

    @BeforeEach
    private void prepData() {
        persistencePort = new MockPersistencePort();
    }

    @Test
    public void testValidInput() {
        DomainData inputData = new DomainData();
        inputData.field1 = "whatever";
        inputData.field2 = "something not null";

        MagicDomainLogicService coreDataInterpreter = new MagicDomainLogicService(persistencePort);
        Assertions.assertDoesNotThrow(() -> coreDataInterpreter.interpretData(inputData));
    }

    @Test
    public void testInvalidInput() {
        DomainData inputData = new DomainData();
        inputData.field1 = "whatever";
        inputData.field2 = null;

        MagicDomainLogicService coreDataInterpreter = new MagicDomainLogicService(persistencePort);
        Assertions.assertThrows(IllegalArgumentException.class, () -> coreDataInterpreter.interpretData(inputData));
    }
}
