package pe.edu.utp.bodega_rb_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ProximoAVencerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findAll_DeberiaRetornarOk() throws Exception {
    mockMvc.perform(get("/proximosAVencer"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findById_DeberiaRetornar404_CuandoNoExiste() throws Exception {
    mockMvc.perform(get("/proximosAVencer/{id}", 999))
        .andExpect(status().isNotFound());
  }
}
