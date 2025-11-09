package pe.edu.utp.bodega_rb_api.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.dto.LoginRequest;
import pe.edu.utp.bodega_rb_api.dto.TokenResponse;
import pe.edu.utp.bodega_rb_api.security.JwtService;
import pe.edu.utp.bodega_rb_api.service.EmpleadoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private EmpleadoService empleadoService;

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest credentials) throws BadCredentialsException {
    Authentication auth = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(credentials.getDni(), credentials.getPassword()));

    String token = jwtService.generateToken(auth);

    return empleadoService.findByDni(credentials.getDni())
        .map(empleado -> ResponseEntity.ok(new TokenResponse(token, empleado)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
