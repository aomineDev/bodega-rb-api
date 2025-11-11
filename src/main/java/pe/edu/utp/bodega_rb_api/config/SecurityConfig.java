package pe.edu.utp.bodega_rb_api.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.bodega_rb_api.dto.ErrorResponse;
import pe.edu.utp.bodega_rb_api.security.CustomUserDetailsService;
import pe.edu.utp.bodega_rb_api.security.JwtAuthenticationFilter;
import pe.edu.utp.bodega_rb_api.util.enums.RolEnum;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .userDetailsService(userDetailsService)
        .cors(cors -> cors.configurationSource(configurationSource()))
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login", "/api/auth/logout", "/api/storage/**", "/storage/**").permitAll()
            .requestMatchers("/api/inventarios")
            .hasAnyRole(RolEnum.ADMINISTRADOR.toString(), RolEnum.JEFE_ALMACEN.toString(), RolEnum.ASISTENTE.toString())
            .requestMatchers("/api/proveedores")
            .hasAnyRole(RolEnum.ADMINISTRADOR.toString(), RolEnum.JEFE_ALMACEN.toString(), RolEnum.CAJERO.toString())
            .requestMatchers("/api/productos")
            .hasAnyRole(RolEnum.ADMINISTRADOR.toString(), RolEnum.JEFE_ALMACEN.toString(), RolEnum.CAJERO.toString())
            .anyRequest().authenticated())
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint(this::authenticationEntryPoint)
            .accessDeniedHandler(this::accessDeniedHandler))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource configurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(
        List.of("http://localhost:5173", "https://localhost:5173", "http://127.0.0.1:5500",
            "https://7smtn079-5173.brs.devtunnels.ms"));

    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

    configuration.setAllowedHeaders(List.of("*"));

    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  private void authenticationEntryPoint(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");

    ErrorResponse errorResponse = new ErrorResponse("Unauthorized", "Acceso no autorizado",
        HttpStatus.UNAUTHORIZED.value());

    ObjectMapper mapper = new ObjectMapper();

    response.getWriter().write(mapper.writeValueAsString(errorResponse));
  }

  private void accessDeniedHandler(HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");

    ErrorResponse errorResponse = new ErrorResponse("Denied access",
        "No tienes permisos para acceder a este recurso",
        HttpStatus.FORBIDDEN.value());

    ObjectMapper mapper = new ObjectMapper();

    response.getWriter().write(mapper.writeValueAsString(errorResponse));

  }
}