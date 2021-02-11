package com.jdriven.adapters.persistence.utility;

import com.jdriven.domain.models.DomainData;

/**
 * An arguably handy utility class that really shouldn't be available outside of this module
 */
public class SuperHandyUtil {

    public static String extractConcatenatedFields(DomainData domainData) {
        if (domainData == null) {
            return "";
        }
        return domainData.field1 + domainData.field2;
    }
}
