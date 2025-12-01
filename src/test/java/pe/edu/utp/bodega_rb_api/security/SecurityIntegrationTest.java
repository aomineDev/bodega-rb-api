package pe.edu.utp.bodega_rb_api.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.EmpleadoRepository;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class SecurityIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private RolRepository rolRepository;

  @Autowired
  private EmpleadoRepository empleadoRepository;

  @Test
  void accesoPublico_DeberiaPermitirAccesoSinToken() throws Exception {
    mockMvc.perform(post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"dni\":\"00000000\",\"password\":\"wrong\"}"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void accesoProtegido_SinToken_DeberiaRetornar401() throws Exception {
    mockMvc.perform(get("/api/roles"))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.message").value("Acceso no autorizado"));
  }

  @Test
  void accesoProtegido_TokenInvalido_DeberiaRetornar401() throws Exception {
    mockMvc.perform(get("/api/roles")
        .header("Authorization", "Bearer token_invalido_12345"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "cajero", roles = { "CAJERO" })
  void accesoProtegido_RolInsuficiente_DeberiaRetornar403() throws Exception {

    mockMvc.perform(get("/api/inventarios"))
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$.message").value("No tienes permisos para acceder a este recurso"));
  }

  @Test
  void login_SqlInjection_DeberiaFallar() throws Exception {
    String sqlInjectionPayload = "{\"dni\":\"' OR '1'='1\",\"password\":\"password\"}";

    mockMvc.perform(post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(sqlInjectionPayload))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void crearRol_XSS_DeberiaGuardarComoTextoPlano() throws Exception {
    String xssPayload = "<script>alert('hacked')</script>";
    String json = "{\"nombre\":\"" + xssPayload + "\"}";

    mockMvc.perform(post("/api/roles")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre").value(xssPayload));
  }

  @Test
  void accesoConTokenReal_DeberiaFuncionar() throws Exception {
    Rol rolAdmin = new Rol(null, "ROLE_ADMINISTRADOR");
    rolAdmin = rolRepository.save(rolAdmin);

    Empleado admin = new Empleado();
    admin.setDni("88888888");
    admin.setNombre("Admin");
    admin.setApellidoPaterno("Test");
    admin.setApellidoMaterno("Test");
    admin.setClave("password");
    admin.setRol(rolAdmin);

    empleadoRepository.save(admin);

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        "88888888", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR")));

    String token = jwtService.generateToken(auth);

    mockMvc.perform(get("/api/roles")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }
}
