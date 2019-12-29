package com.tutorialspoint.websecurityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer

/**
 * Now, in the main Spring Boot application, add the @EnableAuthorizationServer
 * and @EnableResourceServer annotation to act as an Auth server and
 * Resource Server in the same application.
 *
 * Also, you can use the following code to write a simple HTTP endpoint
 * to access the API with Spring Security by using JWT Token.
 */
public class WebsecurityappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsecurityappApplication.class, args);
    }


}
