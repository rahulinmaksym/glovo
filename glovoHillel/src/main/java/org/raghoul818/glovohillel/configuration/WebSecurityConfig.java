package org.raghoul818.glovohillel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN1")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN2")
                        .requestMatchers(HttpMethod.POST).hasRole("USER")
                        .anyRequest().authenticated())
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String admin1Password = encoder.encode("admin1");
        String admin2Password = encoder.encode("admin2");
        String userPassword = encoder.encode("user");

        UserDetails admin1 =
                User
                        .withUsername("admin1")
                        .password(admin1Password)
                        .roles("ADMIN1")
                        .build();

        UserDetails admin2 =
                User
                        .withUsername("admin2")
                        .password(admin2Password)
                        .roles("ADMIN2")
                        .build();

        UserDetails user =
                User
                        .withUsername("user")
                        .password(userPassword)
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin1, admin2, user);
    }
}
