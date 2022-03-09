package carara.salesscoresystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("v1.0.0") String appVersion) {
        Contact contact = new Contact();
        contact.setName("Pedro Carara");
        contact.setUrl("https:github.com/cararax");
        contact.setEmail("cararax@gmail.com");
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Local server");
        ArrayList<Server> servers = new ArrayList<>();
        servers.add(server);

        return new OpenAPI().info(new Info()
                        .title(" Sales Score System ")
                        .version(appVersion)
                        .description("Sales Score System is an API responsible for managing a company's products, vendors and sales")
                        .contact(contact))
                .servers(servers);
    }
}