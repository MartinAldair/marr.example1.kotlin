package io.swagger.server.api.authentication

import io.swagger.server.models.AccessApiKeyResponse
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

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

}