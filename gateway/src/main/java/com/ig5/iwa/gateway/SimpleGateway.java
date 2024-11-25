package com.ig5.iwa.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
public class SimpleGateway {
    @Bean
    public RouterFunction<ServerResponse> routeActivityServicetest() {
        return route("activities")
                .GET("/activities", http("http://localhost:8086/api/activities"))
                .build();
    }
}
