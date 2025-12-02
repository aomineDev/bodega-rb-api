package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;
import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.model.ProximoAVencer;
import pe.edu.utp.bodega_rb_api.repository.ProximoAVencerRepository;
import pe.edu.utp.bodega_rb_api.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProximoAVencerServiceImplTest {

  @Mock
  private ProximoAVencerRepository proximosAVencerRepository;

  @Mock
  private ProductoService productoService;

  @InjectMocks
  private ProximoAVencerServiceImpl proximosAVencerService;

  @Test
  void findAll_DeberiaRetornarLista() {
    ProximoAVencer p1 = new ProximoAVencer();
    p1.setId(1);
    when(proximosAVencerRepository.findAll()).thenReturn(Arrays.asList(p1));

    List<ProximoAVencer> resultado = proximosAVencerService.findAll();

    assertThat(resultado).hasSize(1);
  }

  @Test
  void findById_DeberiaRetornarElemento() {
    Integer id = 1;
    ProximoAVencer p = new ProximoAVencer();
    p.setId(id);
    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.of(p));

    Optional<ProximoAVencer> resultado = proximosAVencerService.findById(id);

    assertThat(resultado).isPresent();
  }

  @Test
  void save_DeberiaGuardar() {
    ProximoAVencer p = new ProximoAVencer();
    when(proximosAVencerRepository.save(any(ProximoAVencer.class))).thenReturn(p);

    ProximoAVencer resultado = proximosAVencerService.save(p);

    assertThat(resultado).isNotNull();
  }

  @Test
  void handle2x1_DeberiaActualizarStockYEliminar_Unidad() {
    Integer id = 1;
    ProximoAVencer p = new ProximoAVencer();
    p.setId(id);
    p.setCantidad(10.0);

    Producto producto = new Producto();
    producto.setId(100);
    producto.setStock(20.0);
    producto.setUnidadMedida("unidad");
    p.setProducto(producto);

    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.of(p));

    proximosAVencerService.handle2x1(id);

    // halfStock = floor(10 / 2) = 5.0
    // newStock = 20.0 - 5.0 = 15.0
    verify(productoService).updateStockById(100, 15.0);
    verify(proximosAVencerRepository).deleteById(id);
  }

  @Test
  void handle2x1_DeberiaActualizarStockYEliminar_Kilo() {
    Integer id = 1;
    ProximoAVencer p = new ProximoAVencer();
    p.setId(id);
    p.setCantidad(10.0);

    Producto producto = new Producto();
    producto.setId(100);
    producto.setStock(20.0);
    producto.setUnidadMedida("kilo");
    p.setProducto(producto);

    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.of(p));

    proximosAVencerService.handle2x1(id);

    // halfStock = 10 / 2 = 5.0
    // newStock = 20.0 - 5.0 = 15.0
    verify(productoService).updateStockById(100, 15.0);
    verify(proximosAVencerRepository).deleteById(id);
  }

  @Test
  void handleOfferPrice_DeberiaActualizarOfertaYEliminar() {
    Integer id = 1;
    Double precio = 5.0;

    ProximoAVencer p = new ProximoAVencer();
    p.setId(id);
    p.setFechaVencimiento(LocalDate.now().plusDays(5));

    Producto producto = new Producto();
    producto.setId(100);
    p.setProducto(producto);

    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.of(p));

    proximosAVencerService.handleOfferPrice(id, precio);

    verify(productoService).updateOfferById(eq(100), eq(precio), any(LocalDate.class));
    verify(proximosAVencerRepository).deleteById(id);
  }

  @Test
  void deleteById_DeberiaEliminar_CuandoExiste() {
    Integer id = 1;
    ProximoAVencer p = new ProximoAVencer();
    p.setId(id);
    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.of(p));

    proximosAVencerService.deleteById(id);

    verify(proximosAVencerRepository).deleteById(id);
  }

  @Test
  void deleteById_DeberiaLanzarExcepcion_CuandoNoExiste() {
    Integer id = 1;
    when(proximosAVencerRepository.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> proximosAVencerService.deleteById(id))
        .isInstanceOf(ResourceNotFoundException.class);
  }
}
