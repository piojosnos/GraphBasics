package org.cyrano.dsa.graph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // --------------------------------------------------------------------------------

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    // --------------------------------------------------------------------------------
    // TODO: Simple ObjectMapper Factory, ultimately this should be injected
    // --------------------------------------------------------------------------------

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
