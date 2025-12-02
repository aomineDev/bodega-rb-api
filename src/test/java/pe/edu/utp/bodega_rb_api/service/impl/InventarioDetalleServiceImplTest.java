package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

import pe.edu.utp.bodega_rb_api.dto.InventarioDetalleDTO;
import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;
import pe.edu.utp.bodega_rb_api.repository.InventarioDetalleRepository;

@ExtendWith(MockitoExtension.class)
class InventarioDetalleServiceImplTest {

  @Mock
  private InventarioDetalleRepository inventarioDetalleRepository;

  @InjectMocks
  private InventarioDetalleServiceImpl inventarioDetalleService;

  @Test
  void findAll_DeberiaRetornarListaDeDetalles() {
    InventarioDetalle detalle1 = new InventarioDetalle();
    detalle1.setId(1);
    InventarioDetalle detalle2 = new InventarioDetalle();
    detalle2.setId(2);
    when(inventarioDetalleRepository.findAll()).thenReturn(Arrays.asList(detalle1, detalle2));

    List<InventarioDetalle> resultado = inventarioDetalleService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarDetalle_CuandoExiste() {
    Integer id = 1;
    InventarioDetalle detalle = new InventarioDetalle();
    detalle.setId(id);
    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalle));

    Optional<InventarioDetalle> resultado = inventarioDetalleService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarDetalle() {
    InventarioDetalle nuevoDetalle = new InventarioDetalle();
    InventarioDetalle detalleGuardado = new InventarioDetalle();
    detalleGuardado.setId(1);

    when(inventarioDetalleRepository.save(any(InventarioDetalle.class))).thenReturn(detalleGuardado);

    InventarioDetalle resultado = inventarioDetalleService.save(nuevoDetalle);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(inventarioDetalleRepository).save(any(InventarioDetalle.class));
  }

  @Test
  void updateById_DeberiaActualizarDetalle() {
    Integer id = 1;
    InventarioDetalle detalleExistente = new InventarioDetalle();
    detalleExistente.setId(id);

    InventarioDetalle detalleActualizado = new InventarioDetalle();
    detalleActualizado.setId(id);
    detalleActualizado.setStockSistema(10.0);
    detalleActualizado.setStockFisico(10.0);

    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalleExistente));
    when(inventarioDetalleRepository.save(any(InventarioDetalle.class))).thenReturn(detalleActualizado);

    Optional<InventarioDetalle> resultado = inventarioDetalleService.updateById(id, detalleActualizado);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getStockFisico()).isEqualTo(10.0);
  }

  @Test
  void addStockById_DeberiaAgregarStock() {
    Integer id = 1;
    InventarioDetalle detalleExistente = new InventarioDetalle();
    detalleExistente.setId(id);
    detalleExistente.setStockSistema(10.0);
    detalleExistente.setStockFisico(5.0);

    InventarioDetalleDTO dto = new InventarioDetalleDTO();
    dto.setStock(5.0);

    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalleExistente));
    when(inventarioDetalleRepository.save(any(InventarioDetalle.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    Optional<InventarioDetalle> resultado = inventarioDetalleService.addStockById(id, dto);

    assertThat(resultado).isPresent();
    // 5.0 + 5.0 = 10.0
    assertThat(resultado.get().getStockFisico()).isEqualTo(10.0);
  }

  @Test
  void updateStockById_DeberiaActualizarStock() {
    Integer id = 1;
    InventarioDetalle detalleExistente = new InventarioDetalle();
    detalleExistente.setId(id);
    detalleExistente.setStockSistema(10.0);
    detalleExistente.setStockFisico(5.0);

    InventarioDetalleDTO dto = new InventarioDetalleDTO();
    dto.setStock(20.0);

    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalleExistente));
    when(inventarioDetalleRepository.save(any(InventarioDetalle.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    Optional<InventarioDetalle> resultado = inventarioDetalleService.updateStockById(id, dto);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getStockFisico()).isEqualTo(20.0);
  }

  @Test
  void updateObservacionesById_DeberiaActualizarObservaciones() {
    Integer id = 1;
    InventarioDetalle detalleExistente = new InventarioDetalle();
    detalleExistente.setId(id);

    InventarioDetalleDTO dto = new InventarioDetalleDTO();
    dto.setObservaciones("Nueva observacion");

    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalleExistente));
    when(inventarioDetalleRepository.save(any(InventarioDetalle.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    Optional<InventarioDetalle> resultado = inventarioDetalleService.updateObservacionesById(id, dto);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getObservaciones()).isEqualTo("Nueva observacion");
  }

  @Test
  void deleteById_DeberiaEliminarDetalle_CuandoExiste() {
    Integer id = 1;
    InventarioDetalle detalle = new InventarioDetalle();
    detalle.setId(id);
    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.of(detalle));

    inventarioDetalleService.deleteById(id);

    verify(inventarioDetalleRepository).deleteById(id);
  }

  @Test
  void deleteById_DeberiaLanzarExcepcion_CuandoNoExiste() {
    Integer id = 1;
    when(inventarioDetalleRepository.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> inventarioDetalleService.deleteById(id))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessageContaining("no encontrado");
  }
}
