package com.flockinger.simplebackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    companion object {
        const val ADMN_ROLE = "ADMIN"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/**").hasRole(ADMN_ROLE)
                .and()
                .formLogin()
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService? {
        val admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles(ADMN_ROLE)
                .build()
        return InMemoryUserDetailsManager(admin)
    }
}