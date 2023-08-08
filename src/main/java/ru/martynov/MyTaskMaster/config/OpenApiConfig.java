package ru.martynov.MyTaskMaster.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "todo System Api",
                description = "todo System", version = "1.0.0",
                contact = @Contact(
                        name = "Martynov Egor",
                        email = "b9955047856@gmail.com",
                        url = "https://"
                )
        )
)
public class OpenApiConfig {
}
