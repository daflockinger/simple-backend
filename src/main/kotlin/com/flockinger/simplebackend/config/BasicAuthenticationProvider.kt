package com.flockinger.simplebackend.config

import com.flockinger.simplebackend.config.SecurityConfig.Companion.ADMN_ROLE
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component


@Component
class BasicAuthenticationProvider(
        private val userDetailsService: UserDetailsService,
): AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication? {
        val name: String = authentication.name
        val password: String = authentication.credentials.toString()
        val storedPassword = userDetailsService.loadUserByUsername(name).password

        return if (storedPassword == password){
            UsernamePasswordAuthenticationToken(name, password, listOf(SimpleGrantedAuthority(ADMN_ROLE)))
        } else {
            null
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}