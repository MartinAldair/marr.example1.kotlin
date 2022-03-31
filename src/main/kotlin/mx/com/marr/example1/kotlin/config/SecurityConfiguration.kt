package mx.com.marr.example1.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
// https://waynestalk.com/en/spring-security-jwt-jpa-springdoc-explained-en/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(web: WebSecurity) {
        // Tell Spring to ignore securing the handshake endpoint. This allows the handshake to take place unauthenticated
        web.ignoring().antMatchers(
            "/",
            "/csrf",
            "/swagger/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/configuration/**",
            "/v3/api-docs/**")
    }

    override fun configure(http: HttpSecurity) {

        // Enable CORS and disable CSRF
        http
            .cors()
            .and()
            .csrf().disable();

        http
            .formLogin().disable()
            .httpBasic().disable();

        // Based on token, so no session is required
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

        // Set permissions on endpoints
        http
            .authorizeRequests()
            // Our public endpoints
            .antMatchers("/authentication/**").permitAll()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
    }

}