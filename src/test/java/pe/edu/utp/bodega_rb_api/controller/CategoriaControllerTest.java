package pe.edu.utp.bodega_rb_api.controller;

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

import pe.edu.utp.bodega_rb_api.model.Categoria;
import pe.edu.utp.bodega_rb_api.repository.CategoriaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CategoriaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    categoriaRepository.deleteAll();
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void listarCategorias_DeberiaRetornar200YLista() throws Exception {
    Categoria cat1 = new Categoria(null, "Bebidas", "Bebidas gaseosas");
    Categoria cat2 = new Categoria(null, "Snacks", "Papas fritas");

    categoriaRepository.save(cat1);
    categoriaRepository.save(cat2);

    mockMvc.perform(get("/api/categorias"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].nombre", is("Bebidas")))
        .andExpect(jsonPath("$[0].descripcion", is("Bebidas gaseosas")))
        .andExpect(jsonPath("$[1].nombre", is("Snacks")))
        .andExpect(jsonPath("$[1].descripcion", is("Papas fritas")));
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findById_CuandoExiste_DeberiaRetornar200YCategoria() throws Exception {
    // Arrange
    Categoria categoria = new Categoria(null, "Lácteos", "Productos lácteos");
    Categoria saved = categoriaRepository.save(categoria);

    // Act & Assert
    mockMvc.perform(get("/api/categorias/{id}", saved.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre", is("Lácteos")))
        .andExpect(jsonPath("$.descripcion", is("Productos lácteos")));
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void findById_CuandoNoExiste_DeberiaRetornar404() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/api/categorias/{id}", 999))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void save_DeberiaCrearCategoria() throws Exception {
    // Arrange
    Categoria nuevaCategoria = new Categoria(null, "Carnes", "Productos cárnicos");

    // Act & Assert
    mockMvc.perform(post("/api/categorias")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(nuevaCategoria)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre", is("Carnes")))
        .andExpect(jsonPath("$.descripcion", is("Productos cárnicos")))
        .andExpect(jsonPath("$.id").exists());

    // Verificar que se guardó en la base de datos
    assert categoriaRepository.findAll().size() == 1;
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void update_DeberiaActualizarCategoria() throws Exception {
    // Arrange
    Categoria categoria = new Categoria(null, "Frutas", "Frutas frescas");
    Categoria saved = categoriaRepository.save(categoria);

    Categoria actualizada = new Categoria(saved.getId(), "Frutas y Verduras", "Frutas y verduras frescas");

    // Act & Assert
    mockMvc.perform(put("/api/categorias/{id}", saved.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(actualizada)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre", is("Frutas y Verduras")))
        .andExpect(jsonPath("$.descripcion", is("Frutas y verduras frescas")));

    // Verificar que se actualizó en la base de datos
    Categoria fromDb = categoriaRepository.findById(saved.getId()).orElseThrow();
    assert fromDb.getNombre().equals("Frutas y Verduras");
  }

  @Test
  @WithMockUser(username = "admin", roles = { "ADMINISTRADOR" })
  void deleteById_DeberiaEliminarCategoria() throws Exception {
    // Arrange
    Categoria categoria = new Categoria(null, "Congelados", "Productos congelados");
    Categoria saved = categoriaRepository.save(categoria);

    // Act & Assert
    mockMvc.perform(delete("/api/categorias/{id}", saved.getId()))
        .andExpect(status().isNoContent());

    // Verificar que se eliminó de la base de datos
    assert categoriaRepository.findById(saved.getId()).isEmpty();
  }
}
