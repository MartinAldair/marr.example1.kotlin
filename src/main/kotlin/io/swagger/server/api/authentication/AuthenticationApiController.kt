package io.swagger.server.api.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.server.models.AccessApiKeyResponse
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.NotNull


@RestController
@Tag(name = "authentication", description = "Operations about authentication")
class AuthenticationApiController : AuthenticationApi {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Autowired
    private val request: HttpServletRequest? = null

    override fun loginUser(
        @NotNull @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The recaptcha for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "recaptcha", required = true) recaptcha: @Valid String?,
        @NotNull @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The email for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "email", required = true) email: @Valid String?,
        @NotNull @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The password for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "password", required = true) password: @Valid String?
    ): ResponseEntity<AccessApiKeyResponse?>? {
        val accept = request!!.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {



                ResponseEntity(
                    objectMapper!!.readValue(
                        "{\n  \"apiKey\" : \"apiKey\"\n}",
                        AccessApiKeyResponse::class.java
                    ), HttpStatus.OK
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.OK)
    }

}