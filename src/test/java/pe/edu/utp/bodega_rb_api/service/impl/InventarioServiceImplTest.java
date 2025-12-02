package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;
import pe.edu.utp.bodega_rb_api.model.Categoria;
import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Inventario;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;
import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.InventarioRepository;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class InventarioServiceImplTest {

  @Mock
  private InventarioRepository inventarioRepository;

  @Mock
  private ProductoRepository productoRepository;

  @InjectMocks
  private InventarioServiceImpl inventarioService;

  @Test
  void findAll_DeberiaRetornarListaDeInventarios() {
    Inventario inventario1 = new Inventario();
    inventario1.setId(1);
    Inventario inventario2 = new Inventario();
    inventario2.setId(2);
    when(inventarioRepository.findAll()).thenReturn(Arrays.asList(inventario1, inventario2));

    List<Inventario> resultado = inventarioService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findByEstado_DeberiaRetornarListaDeInventariosPorEstado() {
    Boolean estado = true;
    Inventario inventario1 = new Inventario();
    when(inventarioRepository.findByEstado(estado)).thenReturn(Arrays.asList(inventario1));

    List<Inventario> resultado = inventarioService.findByEstado(estado);

    assertThat(resultado).hasSize(1);
  }

  @Test
  void findById_DeberiaRetornarInventario_CuandoExiste() {
    Integer id = 1;
    Inventario inventario = new Inventario();
    inventario.setId(id);
    when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));

    Optional<Inventario> resultado = inventarioService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarInventario_YCrearDetalles() {
    Categoria categoria = new Categoria();
    categoria.setId(1);

    Inventario nuevoInventario = new Inventario();
    nuevoInventario.setCategoria(categoria);
    nuevoInventario.setInventarioDetalles(new ArrayList<>());

    Producto producto1 = new Producto();
    producto1.setId(1);
    producto1.setStock(10.0);

    when(productoRepository.findByCategoria_Id(1)).thenReturn(Arrays.asList(producto1));
    when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Inventario resultado = inventarioService.save(nuevoInventario);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getInventarioDetalles()).hasSize(1);
    assertThat(resultado.getInventarioDetalles().get(0).getProducto().getId()).isEqualTo(1);
    assertThat(resultado.getInventarioDetalles().get(0).getStockSistema()).isEqualTo(10.0);
  }

  @Test
  void updateById_DeberiaActualizarInventario() {
    Integer id = 1;
    Inventario inventarioExistente = new Inventario();
    inventarioExistente.setId(id);

    Inventario inventarioActualizado = new Inventario();
    inventarioActualizado.setId(id);
    // inventarioActualizado.setObservaciones("Updated");

    when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventarioExistente));
    when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioActualizado);

    Optional<Inventario> resultado = inventarioService.updateById(id, inventarioActualizado);

    assertThat(resultado).isPresent();
    // assertThat(resultado.get().getObservaciones()).isEqualTo("Updated");
  }

  @Test
  void addAsistenteAlmacen_DeberiaAgregarAsistente() {
    Integer id = 1;
    Inventario inventario = new Inventario();
    inventario.setId(id);
    inventario.setAsistenteAlmacenList(new ArrayList<>());

    Empleado asistente = new Empleado();
    asistente.setId(2);

    when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));
    when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Optional<Inventario> resultado = inventarioService.addAsistenteAlmacen(id, asistente);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getAsistenteAlmacenList()).hasSize(1);
  }

  @Test
  void cerrarInventario_DeberiaCerrarInventario_YActualizarStockProductos() {
    Integer id = 1;
    Inventario inventario = new Inventario();
    inventario.setId(id);
    inventario.setEstado(true);

    Producto producto = new Producto();
    producto.setId(10);
    producto.setStock(10.0);

    InventarioDetalle detalle = new InventarioDetalle();
    detalle.setProducto(producto);
    detalle.setStockSistema(10.0);
    detalle.setStockFisico(8.0);

    List<InventarioDetalle> detalles = new ArrayList<>();
    detalles.add(detalle);
    inventario.setInventarioDetalles(detalles);

    when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));
    when(productoRepository.findById(10)).thenReturn(Optional.of(producto));
    when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));
    when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Optional<Inventario> resultado = inventarioService.cerrarInventario(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getEstado()).isFalse();
    assertThat(resultado.get().getFechaInventario()).isEqualTo(LocalDate.now());

    // Verify product stock updated
    verify(productoRepository).save(any(Producto.class));
    assertThat(producto.getStock()).isEqualTo(8.0);
  }

  @Test
  void deleteById_DeberiaEliminarInventario_CuandoExiste() {
    Integer id = 1;
    Inventario inventario = new Inventario();
    inventario.setId(id);
    when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));

    inventarioService.deleteById(id);

    verify(inventarioRepository).deleteById(id);
  }

  @Test
  void deleteById_DeberiaLanzarExcepcion_CuandoNoExiste() {
    Integer id = 1;
    when(inventarioRepository.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> inventarioService.deleteById(id))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessageContaining("no encontrado");
  }
}
