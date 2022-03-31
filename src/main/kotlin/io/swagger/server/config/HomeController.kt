package io.swagger.server.config

import org.springdoc.core.Constants.SWAGGER_UI_PATH
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX

@Controller
class HomeController {

    @Value(SWAGGER_UI_PATH)
    private val swaggerUiPath: String? = null

    @GetMapping(DEFAULT_PATH_SEPARATOR)
    fun index(): String? {
        return REDIRECT_URL_PREFIX + swaggerUiPath
    }

}