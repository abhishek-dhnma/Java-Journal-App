package net.engineeringdigest.journalApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI CustomSwaggerConfig(){
         return new OpenAPI()
                 .info(
                new Info().title("Journal App APIs")
                        .description("Journal App! Start journaling Today!"))

                 .servers(Arrays.asList(new Server().url("http://localhost:8080/journal/").description("LOCAL"),
                         new Server().url("https://rocky-sea-69277-cfa5900c8a11.herokuapp.com/journal/").description("PROD")));

    }

}
