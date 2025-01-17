package com.cogent.blogrestapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeHttpRequests((authorize) ->
//                        authorize
//                                .anyRequest()
//                                .authenticated())
                            authorize.requestMatchers(HttpMethod.GET, "/api/**" ).permitAll()
                                    .requestMatchers("/api/auth/**").permitAll()
                                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails mark = User.builder()
//                .username("mark" )
//                //.password("123")
//                .password(passwordEncoder().encode("123" ))
//                .roles("USER" )
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin" )
//                //.password("123")
//                .password(passwordEncoder().encode("123" ))
//                .roles("ADMIN" )
//                .build();
//        return new InMemoryUserDetailsManager(mark, admin);
//    }

}
