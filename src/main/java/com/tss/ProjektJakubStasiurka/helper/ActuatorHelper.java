package com.tss.ProjektJakubStasiurka.helper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

@Component
public class ActuatorHelper {

    private final InfoEndpoint infoEndpoint;
    private final WebMvcEndpointHandlerMapping endpointHandlerMapping;

    public ActuatorHelper(
            InfoEndpoint infoEndpoint,
            WebMvcEndpointHandlerMapping endpointHandlerMapping
    ) {
        this.infoEndpoint = infoEndpoint;
        this.endpointHandlerMapping = endpointHandlerMapping;
    }

    public Map<String, Object> getInfo() {
        return infoEndpoint.info();
    }

    public List<String> getEndpoints() {
        return endpointHandlerMapping.getEndpoints().stream()
                .map(endpoint -> "/actuator/" + endpoint.getRootPath())
                .sorted()
                .collect(Collectors.toList());
    }
}
