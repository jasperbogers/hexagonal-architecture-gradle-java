package com.jdriven.adapters.inputhandler;

import com.jdriven.domain.models.DomainData;
import com.jdriven.domain.ports.DataInterpretationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Adapter for data ingress from a file and passing the data to the core logic
 */
public class FileInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(FileInputHandler.class);

    private final DataInterpretationPort dataInterpretationPort;

    public FileInputHandler(DataInterpretationPort dataInterpretationPort) {
        this.dataInterpretationPort = dataInterpretationPort;
    }

    /**
     * Private class for storing data read from file
     */
    private static class FileReaderAdapterData {
        private String field1;
        private String field2;

        public void setFields(String[] values) {
            if (values == null || values.length == 0) {
                logger.warn("No values found");
                return;
            };

            try {
                field1 = values[0];
                field2 = values[1];
            } catch(ArrayIndexOutOfBoundsException e) {
                logger.warn("Some values are missing.");
            }
        }

        /**
         * Convert internal data to a format accepted by core logic
         * @return DomainData
         */
        public DomainData toCoreData() {
            DomainData domainData = new DomainData();
            domainData.field1 = field1;
            domainData.field2 = field2;
            return domainData;
        }
    }

    /**
     * Take data from a file.
     * @param fileName Name of a file expected to be in the classpath, for example in /src/main/resources
     */
    public void takeInput(String fileName) {
        String csvFile = fileName;
        String line;
        String cvsSplitBy = ",";
        FileReaderAdapterData data = new FileReaderAdapterData();

        logger.info("Started taking input.");
        InputStream is = getClass().getClassLoader().getResourceAsStream(csvFile);
        if (is == null) {
            logger.warn("Failed to find file: {}", csvFile);
            return;
        }
        try (InputStreamReader streamReader = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            if ((line = bufferedReader.readLine()) != null) {
                data.setFields(line.split(cvsSplitBy));
            }
        } catch (IOException e) {
            logger.warn("Failed to take input: {}", e.getMessage());
            return;
        }

        logger.info("Sending input to interpreter.");
        try {
            dataInterpretationPort.interpretData(data.toCoreData());
        } catch (IllegalArgumentException e) {
            // TODO Questionable if handling this kind of exception in any more depth is desirable in a domain agnostic adapter
            logger.warn("Interpreter rejected the data.");
        }
    }
}