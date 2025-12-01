package pe.edu.utp.bodega_rb_api.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class RolControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RolRepository rolRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    rolRepository.deleteAll();
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findAll_DeberiaRetornarListaDeRoles() throws Exception {
    Rol rol1 = new Rol(null, "ROLE_ADMINISTRADOR");
    Rol rol2 = new Rol(null, "ROLE_CAJERO");

    rolRepository.save(rol1);
    rolRepository.save(rol2);

    mockMvc.perform(get("/api/roles"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].nombre", is("ROLE_ADMINISTRADOR")))
        .andExpect(jsonPath("$[1].nombre", is("ROLE_CAJERO")));
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findById_DeberiaRetornarRol_CuandoExiste() throws Exception {
    Rol rol = new Rol(null, "ROLE_ADMINISTRADOR");
    Rol saved = rolRepository.save(rol);

    mockMvc.perform(get("/api/roles/{id}", saved.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(saved.getId())))
        .andExpect(jsonPath("$.nombre", is("ROLE_ADMINISTRADOR")));
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findById_DeberiaRetornar404_CuandoNoExiste() throws Exception {
    mockMvc.perform(get("/api/roles/{id}", 999))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void save_DeberiaCrearNuevoRol() throws Exception {
    Rol nuevoRol = new Rol(null, "ROLE_ASISTENTE");

    mockMvc.perform(post("/api/roles")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(nuevoRol)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre", is("ROLE_ASISTENTE")))
        .andExpect(jsonPath("$.id").exists());

    assert rolRepository.findAll().size() == 1;
    assert rolRepository.findAll().get(0).getNombre().equals("ROLE_ASISTENTE");
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void update_DeberiaActualizarRolExistente() throws Exception {
    Rol rol = new Rol(null, "ROLE_CAJERO");
    Rol saved = rolRepository.save(rol);

    Rol rolActualizado = new Rol(saved.getId(), "ROLE_JEFE_ALMACEN");

    mockMvc.perform(put("/api/roles/{id}", saved.getId())
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(rolActualizado)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(saved.getId())))
        .andExpect(jsonPath("$.nombre", is("ROLE_JEFE_ALMACEN")));

    Rol fromDb = rolRepository.findById(saved.getId()).orElseThrow();
    assert fromDb.getNombre().equals("ROLE_JEFE_ALMACEN");
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void deleteById_DeberiaEliminarRol() throws Exception {
    Rol rol = new Rol(null, "ROLE_TEMPORAL");
    Rol saved = rolRepository.save(rol);

    mockMvc.perform(delete("/api/roles/{id}", saved.getId())
        .with(csrf()))
        .andExpect(status().isNoContent());

    assert rolRepository.findById(saved.getId()).isEmpty();
  }
}
