package io.swagger.server.models

data class UserParams(
    val recaptcha: kotlin.String,
    val username: kotlin.String,
    val email: kotlin.String,
    val password: kotlin.String,
    val role: kotlin.String
)
