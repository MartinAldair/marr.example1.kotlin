package io.swagger.server.api.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.server.models.AccessApiKeyResponse
import io.swagger.server.models.UserParams
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import mx.com.marr.example1.kotlin.service.impl.UserServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
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
    val objectMapper: ObjectMapper? = null

    @Autowired
    val request: HttpServletRequest? = null

//    @Autowired
//    var userServiceImpl: UserServiceImpl? = null

    override fun loginUser(
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The recaptcha for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "recaptcha", required = true) recaptcha: @NotNull @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The email for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "email", required = true) email: @NotNull @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "The password for login",
            required = true,
            schema = Schema()
        ) @RequestParam(value = "password", required = true) password: @NotNull @Valid String?
    ): ResponseEntity<AccessApiKeyResponse?>? {
        val accept = request!!.getHeader("Accept")
        return if (accept != null || accept!!.contains("application/xml") || accept!!.contains("application/json")) {
            try {

                var accessApiKeyResponse = AccessApiKeyResponse("key");
                val json = objectMapper!!.writeValueAsString(accessApiKeyResponse);
                ResponseEntity(
                    objectMapper!!.readValue(
                        json,
                        AccessApiKeyResponse::class.java
                    ), HttpStatus.OK
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/xml | application/json", e);
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.OK)
    }

    override fun signupUser(
        @Parameter(
            `in` = ParameterIn.DEFAULT,
            description = "Created an user params definition",
            required = true,
            schema = Schema()
        ) @RequestBody body: @Valid UserParams?
    ): ResponseEntity<Void?>? {
        val accept = request!!.getHeader("Accept")
        return if (accept != null) {
            try {

//               this.userServiceImpl!!.existsUserByEmail(body!!.email);

            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            log.error("Couldn't serialize response for content type application/xml | application/json", e);
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else ResponseEntity(HttpStatus.OK)
    }
}