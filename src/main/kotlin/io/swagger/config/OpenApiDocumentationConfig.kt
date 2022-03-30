package io.swagger.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class OpenApiDocumentationConfig {

    private val API_KEY = "api_key"

    @Value("\${openapi.project.title}")
    private val title: String? = null

    @Value("\${openapi.project.description}")
    private val description: String? = null

    @Value("\${openapi.project.version}")
    private val version: String? = null

    @Value("\${openapi.project.terms-of-service-url}")
    private val termOfServiceUrl: String? = null

    @Value("\${openapi.project.contact.name}")
    private val contactName: String? = null

    @Value("\${openapi.project.contact.email}")
    private val contactEmail: String? = null

    @Value("\${openapi.project.contact.url}")
    private val contactUrl: String? = null

    @Value("\${openapi.project.license.name}")
    private val licenseName: String? = null

    @Value("\${openapi.project.license.url}")
    private val licenseUrl: String? = null

    @Value("\${openapi.project.external-documentation-description}")
    private val documentationDescription: String? = null

    @Value("\${openapi.project.external-documentation-url}")
    private val documentationUrl: String? = null

    @Bean
    fun customOpenAPI(): OpenAPI? {
        val localServer = Server()
        localServer.setDescription("local")
        localServer.setUrl("http://localhost:8080")
        val testServer = Server()
        testServer.setDescription("test")
        testServer.setUrl("https://example.org")
        val openAPI = OpenAPI()
        openAPI.components = components()
        openAPI.info(info())
        openAPI.externalDocs = externalDocumentation()
        openAPI.servers = Arrays.asList(localServer, testServer)
        return openAPI
    }

    fun components(): Components? {
        return Components()
            .addSecuritySchemes(
                API_KEY, SecurityScheme()
                    .name(API_KEY)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
    }

    fun info(): Info? {
        return Info()
            .title(title)
            .description(description)
            .termsOfService(termOfServiceUrl)
            .version(version)
            .contact(
                Contact()
                    .name(contactName)
                    .url(contactUrl)
                    .email(contactEmail)
            )
            .license(
                License()
                    .name(licenseName)
                    .url(licenseUrl)
            )
    }

    fun externalDocumentation(): ExternalDocumentation? {
        return ExternalDocumentation()
            .description(documentationDescription)
            .url(documentationUrl)
    }

}