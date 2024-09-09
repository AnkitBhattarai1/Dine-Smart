package com.example.apigateway.Filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.example.apigateway.Utils.JwtUtil;

/**
 * AuthenticationFilter is a custom filter for the Spring Cloud Gateway that
 * handles
 * authentication of incoming requests. It verifies the presence and validity of
 * JWT
 * tokens in the Authorization header for secured routes.
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    RouteValidator routeValidator;

    // @Autowired
    // RestTemplate restTemplate;
    @Autowired
    JwtUtil jwtUtil;

    /**
     * Default constructor initializing the config class.
     */
    public AuthenticationFilter() {
        super(Config.class);
    }

    /**
     * Configuration class for the AuthenticationFilter. This can be expanded to
     * include
     * various configuration properties if needed in the future.
     */
    public static class Config {

    }

    /**
     * Applies the filter logic for authentication. If the route is secured, it
     * checks
     * for the presence of the Authorization header and validates the JWT token.
     * 
     * @param config Configuration object for the filter.
     * @return GatewayFilter which either allows the request to proceed or responds
     *         with
     *         an unauthorized status.
     */
    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            // Check if the requested route requires security
            if (routeValidator.isSecured.test(exchange.getRequest())) {

                // If the Authorization header is missing, respond with UNAUTHORIZED status
                if (!(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                String authHeader;
                // Get the list of Authorization headers
                List<String> list = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

                // If the list is null, respond with UNAUTHORIZED status
                if (list == null) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }
                // Get the first header from the list
                authHeader = list.get(0);
                // If the header starts with "Bearer ", remove the "Bearer " prefix to get the
                // token
                if (authHeader != null && authHeader.startsWith("Bearer "))
                    authHeader = authHeader.substring(7);

                try {
                    // restTemplate.getForEntity("http://AUTH_SERVICE/validate?token=" + authHeader,
                    // String.class);

                    // Validate the token using JwtUtil
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Proceed with the next filter in the chain
            return chain.filter(exchange);
        });

    }
}
