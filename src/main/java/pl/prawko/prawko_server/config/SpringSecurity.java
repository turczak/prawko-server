package pl.prawko.prawko_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring security configuration class for role-based access using basic authentication.
 * <p>
 * It disables CSRF protection and enforces role-based authorization for all endpoints.
 * <p>
 * Rules:
 * <ul>
 *     <li>Only user with the {@code ADMIN} role can upload file using {@code POST /questions}</li>
 *     <li>Allow public access to {@code POST /users} for registration</li>
 * </ul>
 */
@Configuration
public class SpringSecurity {

    /**
     * Configures the application's security filter chain.
     *
     * @param http the {@link HttpSecurity} instance to customize
     * @return a fully configured {@link SecurityFilterChain} with defined rules
     * @throws Exception if an error occurs while building the filter chain
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/questions").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/users").permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
