package io.swagger.server.api.authentication

import io.swagger.server.models.AccessApiKeyResponse
import io.swagger.server.models.UserParams
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid
import javax.validation.constraints.*

interface AuthenticationApi {

    @Operation(
        summary = "Logs user into the system",
        description = "To get a valid response for testing. try recaptcha with value charater, email with value example@mail.com, password with value string.",
        tags = ["authentication"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = arrayOf(
                Content(
                    mediaType = "application/xml",
                    schema = Schema(implementation = AccessApiKeyResponse::class)
                )
            )
        ), ApiResponse(responseCode = "400", description = "Bad Request"), ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
        ), ApiResponse(responseCode = "403", description = "Forbidden"), ApiResponse(
            responseCode = "404",
            description = "Not Found"
        ), ApiResponse(responseCode = "405", description = "Method Not Allowed"), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )]
    )
    @RequestMapping(
        value = ["/authentication/user/login"],
        produces = ["application/xml", "application/json"],
        method = [RequestMethod.GET]
    )
    fun loginUser(
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
    ): ResponseEntity<AccessApiKeyResponse?>?

    @Operation(
        summary = "Create an user to save into the system",
        description = "To get a valid response for testing. try recaptcha with value charater, username with value charaters, email with value example@mail.com, password with value charaters. role with value ROLE_ADMIN | ROLE_USER",
        tags = ["authentication"]
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "OK"), ApiResponse(
            responseCode = "400",
            description = "Bad Request"
        ), ApiResponse(responseCode = "401", description = "Unauthorized"), ApiResponse(
            responseCode = "403",
            description = "Forbidden"
        ), ApiResponse(responseCode = "404", description = "Not Found"), ApiResponse(
            responseCode = "405",
            description = "Method Not Allowed"
        ), ApiResponse(responseCode = "500", description = "Internal Server Error")]
    )
    @RequestMapping(
        value = ["/authentication/user/signup"],
        consumes = ["application/json", "application/xml"],
        method = [RequestMethod.POST]
    )
    fun signupUser(
        @Parameter(
            `in` = ParameterIn.DEFAULT,
            description = "Created an user params definition",
            required = true,
            schema = Schema()
        ) @RequestBody body: @Valid UserParams?
    ): ResponseEntity<Void?>?

}