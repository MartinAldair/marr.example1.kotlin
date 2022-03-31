package io.swagger.server.api.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.server.models.AccessApiKeyResponse
import io.swagger.server.models.UserParams
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import mx.com.marr.example1.kotlin.model.User
import mx.com.marr.example1.kotlin.service.impl.UserServiceImpl
import mx.com.marr.example1.kotlin.support.util.DateUtil
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.*
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

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    var dateUtil: DateUtil? = null

    @Autowired
    var userServiceImpl: UserServiceImpl? = null

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
        if (accept != null || accept!!.contains("application/xml") || accept!!.contains("application/json")) {
            try {
                var isValidCaptcha = true;
                if(!isValidCaptcha) {
                    log.info("Throwing exception as the captcha is invalid.");
                    return ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED)
                }else{
                    var existsUserByEmail = userServiceImpl!!.existsUserByEmail(email!!);
                    if (!existsUserByEmail) {
                        return ResponseEntity(HttpStatus.NOT_FOUND)
                    } else {
                        log.info("" + existsUserByEmail);

                        var accessApiKeyResponse = AccessApiKeyResponse("key");
                        val json = objectMapper!!.writeValueAsString(accessApiKeyResponse);
                        return ResponseEntity(
                            objectMapper!!.readValue(
                                json,
                                AccessApiKeyResponse::class.java
                            ), HttpStatus.OK
                        )
                    }
                }
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/xml | application/json", e);
                return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        return ResponseEntity(HttpStatus.OK)
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
        if (accept != null) {
        try {
                var isValidCaptcha = true;
                if(!isValidCaptcha) {
                    log.info("Throwing exception as the captcha is invalid.");
                    return ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED)
                }else {
                    var existsUserByEmail = userServiceImpl!!.existsUserByEmail(body!!.email);
                    if (existsUserByEmail) {
                        return ResponseEntity(HttpStatus.FORBIDDEN)
                    } else {
                        val currentDate: Date = dateUtil!!.getCurrentDateLocaleMX()
                        val currentEncrytedPassword = BCryptPasswordEncoder().encode(body!!.password)
                        userServiceImpl!!.saveUser( User(
                            id = null,
                            email = body!!.email,
                            password = currentEncrytedPassword,
                            createdAt = currentDate,
                            lastChangedAt = null
                        ))
                        return ResponseEntity(HttpStatus.OK)
                    }
                }
            } catch (e: Exception) {
                log.error("Couldn't serialize response for content type", e);
                return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity(HttpStatus.OK)
    }
}