package com.flockinger.simplebackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class UserDetailsConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetails = User.withUsername("admin")
                .password("admin")
                .roles(SecurityConfig.ADMN_ROLE)
                .passwordEncoder({ it })
                .build()
        return InMemoryUserDetailsManager(userDetails)
    }
}