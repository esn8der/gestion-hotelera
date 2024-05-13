package co.udea.reservation_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server(url = "/") },
        info = @Info(title = "Reservation microservices API Documentation",
                contact = @Contact(email = "mail@mail.com", name = "Demo support team", url = "github.com/esn8der"),
                version = "v1.0.0", description = "This is a demo application for the reservation microservices API documentation.")
)
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${spring.security.oauth2.authorizationserver.endpoint.authorization-uri}",
                tokenUrl = "${spring.security.oauth2.authorizationserver.endpoint.token-uri}",
                scopes = {
                        @OAuthScope(name = "openid", description = "openid"),
                        @OAuthScope(name = "read_access", description = "read access"),
                        @OAuthScope(name = "write_access", description = "write access"),
                })))
public class OpenApiConfig {


}
