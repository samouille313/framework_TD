package edu.spring.dogs.config

import edu.spring.dogs.service.DbUserService
import jakarta.annotation.security.RolesAllowed
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class WebSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
       // fun configure(http: HttpSecurity): SecurityFilterChain {
            http.authorizeHttpRequests { authorizeHttpRequests ->
                authorizeHttpRequests.requestMatchers("/css/**", "/assets/**", "/login/**").permitAll() // (1)
                authorizeHttpRequests.requestMatchers("/admin/**").hasRole("ROLE_ADMIN") // (2)
                authorizeHttpRequests.requestMatchers("/user/**").hasAuthority("USER") // (3)
                authorizeHttpRequests.requestMatchers("/staff/**").hasAnyAuthority("USER", "ADMIN", "MANAGER") // (4)
                authorizeHttpRequests.requestMatchers(PathRequest.toH2Console()).permitAll()
                authorizeHttpRequests.requestMatchers("/master/**").hasRole("manager")
                authorizeHttpRequests.anyRequest().authenticated() // (5)

            }
            http
                .csrf().disable().authorizeHttpRequests()
                .and()
                .formLogin().loginPage("/login")
                .successForwardUrl("/")
                .and()
                .headers().frameOptions().sameOrigin()

            return http.build()
        }


        @Bean
        fun userDetailsService(): UserDetailsService? {
            return DbUserService()
        }

        @Bean
        fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
            return BCryptPasswordEncoder()
        }

    @Bean
    fun roleHierarchy(): RoleHierarchyImpl? {
        val roleHierarchy = RoleHierarchyImpl()
        val hierarchy = ("ROLE_ADMIN > MANAGER_MASTER\n"+
                        "ROLE_ADMIN > MANAGER_DOG\n" +
                        "MANAGER_DOG  > USER\n" +
                        "MANAGER_MASTER > USER"
                )
        roleHierarchy.setHierarchy(hierarchy)
        return roleHierarchy
    }

    @Secured("ROLE_ADMIN")
    fun adminOnly():String {
        return "admin"
    }

    @RolesAllowed(value = [ "ROLE_USER", "ROLE_MANAGER"])
    fun userOrManager():String {
        return "userOrManager"
    }

  


    }