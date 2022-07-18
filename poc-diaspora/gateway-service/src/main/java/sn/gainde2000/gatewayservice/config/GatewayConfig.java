package sn.gainde2000.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sn.gainde2000.gatewayservice.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route(r->r.path("/publicDevises/**")
                        .filters(f->f
                                .addRequestHeader("x-rapidapi-host","currency-exchange.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key","9d236c5439msh683c34f0e66e29cp14f97cjsnd2bd2a702f2f")
                                .rewritePath("/publicDevises/(?<segment>.*)","/${segment}")
                        )
                        .uri("https://currency-exchange.p.rapidapi.com"))
                  .route("users",r ->r.path("/auth/users/**").filters(f->f.filter(filter)).uri("lb://auth"))
                 .route("autfra",r->r.path("/auth1/**").filters(f->f.filter(filter)).uri("lb://fra-service"))
                 .route("demandeaut",r->r.path("/demandeautfra/**").uri("lb://fra-service"))
                .route("permis",r->r.path("/permis/**").filters(f->f.filter(filter)).uri("lb://permis-service"))
                .route("rvmedical",r->r.path("/rvmedical/**").filters(f->f.filter(filter)).uri("lb://rvmedical-service"))
                 .route("auth",r->r.path("/verificationCodeForUser/**","/envoyerotpByMail/**","/modifierPassword/**",
                         "/verificationCode/**","/getInfo/**","/sendMailOtp/**","/tracability/**","/transactions/**").uri("lb://auth"))
                 .route("app",r->r.path("/applications/**","/category/**").filters(f->f.filter(filter)).uri("lb://appplication-service"))
                 //.route("suivreaut",r->r.path("/suivredemande/**").uri("lb://autfra-service"))
                 .route("product",r ->r.path("/product/**").filters(f->f.filter(filter)).uri("lb://product-service"))
                 .route("echo", r -> r.path("/echo/**").filters(f -> f.filter(filter)).uri("lb://echo-service")).build();

    }
}
