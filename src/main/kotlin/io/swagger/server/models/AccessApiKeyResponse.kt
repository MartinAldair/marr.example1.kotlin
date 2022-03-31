package io.swagger.server.models

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

/**
 *  * @param apiKey */
data class AccessApiKeyResponse (@field:Schema(description = "Api key") @field:NotBlank val apiKey: String? = null) {

}
