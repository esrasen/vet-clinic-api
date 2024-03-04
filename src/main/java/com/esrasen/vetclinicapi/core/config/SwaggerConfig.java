package com.esrasen.vetclinicapi.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    @Profile("dev")
    @Bean
    public OpenAPI devOpenAPI() {
        return createOpenAPI("http://localhost:8080", "Development");
    }

    @Profile("prod")
    @Bean
    public OpenAPI prodOpenAPI() {
        return createOpenAPI("\n" +
                "https://patika-spring-b7a23f433143.herokuapp.com/", "Production");
    }
    private OpenAPI createOpenAPI(String url, String description) {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription(description);

        Contact myContact = new Contact();
        myContact.setName("Esra Şen");
        myContact.setUrl("https://github.com/esrasen/vet-clinic-api");

        Info information = new Info()
                .title("Vet Clinic API")
                .version("1.0")
                .description("This API for veterinary management system endpoints.")
                .contact(myContact);
        return new OpenAPI().addServersItem(server)
                .info(information);
    }
}

