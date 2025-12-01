package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.model.Categoria;
import pe.edu.utp.bodega_rb_api.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

  @Mock
  private CategoriaRepository categoriaRepository;

  @InjectMocks
  private CategoriaServiceImpl categoriaService;

  @Test
  void findAll_DeberiaRetornarListaDeCategorias() {
    Categoria cat1 = new Categoria(1, "Bebidas", "Bebidas gaseosas y jugos");
    Categoria cat2 = new Categoria(2, "Snacks", "Papas fritas y piqueos");
    when(categoriaRepository.findAll()).thenReturn(Arrays.asList(cat1, cat2));

    List<Categoria> resultado = categoriaService.findAll();

    assertThat(resultado).hasSize(2);
    assertThat(resultado.get(0).getNombre()).isEqualTo("Bebidas");
  }

  @Test
  void findById_DeberiaRetornarCategoria_CuandoExiste() {
    // Arrange
    Integer id = 1;
    Categoria cat = new Categoria(id, "Bebidas", "Bebidas gaseosas");
    when(categoriaRepository.findById(id)).thenReturn(Optional.of(cat));

    Optional<Categoria> resultado = categoriaService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getNombre()).isEqualTo("Bebidas");
  }

  @Test
  void save_DeberiaGuardarCategoria() {
    Categoria nuevaCat = new Categoria(null, "Limpieza", "Artículos de limpieza");
    Categoria catGuardada = new Categoria(3, "Limpieza", "Artículos de limpieza");

    when(categoriaRepository.save(any(Categoria.class))).thenReturn(catGuardada);

    Categoria resultado = categoriaService.save(nuevaCat);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(3);
    verify(categoriaRepository).save(any(Categoria.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;

    categoriaService.deleteById(id);

    verify(categoriaRepository).deleteById(id);
  }
}
