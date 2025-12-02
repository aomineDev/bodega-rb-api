package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

  @Mock
  private ProductoRepository productoRepository;

  @InjectMocks
  private ProductoServiceImpl productoService;

  @Test
  void findAll_DeberiaRetornarListaDeProductos() {
    Producto p1 = new Producto();
    p1.setId(1);
    Producto p2 = new Producto();
    p2.setId(2);
    when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

    List<Producto> resultado = productoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarProducto_CuandoExiste() {
    Integer id = 1;
    Producto p = new Producto();
    p.setId(id);
    when(productoRepository.findById(id)).thenReturn(Optional.of(p));

    Optional<Producto> resultado = productoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void findByCategoria_Id_DeberiaRetornarListaDeProductos() {
    Integer catId = 1;
    Producto p1 = new Producto();
    when(productoRepository.findByCategoria_Id(catId)).thenReturn(Arrays.asList(p1));

    List<Producto> resultado = productoService.findByCategoria_Id(catId);

    assertThat(resultado).hasSize(1);
  }

  @Test
  void save_DeberiaGuardarProducto() {
    Producto nuevo = new Producto();
    Producto guardado = new Producto();
    guardado.setId(1);

    when(productoRepository.save(any(Producto.class))).thenReturn(guardado);

    Producto resultado = productoService.save(nuevo);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(productoRepository).save(any(Producto.class));
  }

  @Test
  void updateStockById_DeberiaActualizarStock() {
    Integer id = 1;
    Double newStock = 50.0;
    Producto p = new Producto();
    p.setId(id);
    p.setStock(10.0);

    when(productoRepository.findById(id)).thenReturn(Optional.of(p));
    when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Optional<Producto> resultado = productoService.updateStockById(id, newStock);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getStock()).isEqualTo(newStock);
  }

  @Test
  void updateOfferById_DeberiaActualizarOferta() {
    Integer id = 1;
    Double precio = 9.99;
    LocalDate fechaFin = LocalDate.now().plusDays(7);
    Producto p = new Producto();
    p.setId(id);

    when(productoRepository.findById(id)).thenReturn(Optional.of(p));
    when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Optional<Producto> resultado = productoService.updateOfferById(id, precio, fechaFin);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getPrecioPromocion()).isEqualTo(precio);
    assertThat(resultado.get().getInicioPromocion()).isNotNull();
    assertThat(resultado.get().getFinPromocion()).isEqualTo(fechaFin);
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    productoService.deleteById(id);
    verify(productoRepository).deleteById(id);
  }

  @Test
  void descontarStock_DeberiaLlamarRepositorio() {
    Integer id = 1;
    Double cantidad = 5.0;
    productoService.descontarStock(id, cantidad);
    verify(productoRepository).descontarStock(id, cantidad);
  }
}
