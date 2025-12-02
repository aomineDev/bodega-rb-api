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

import pe.edu.utp.bodega_rb_api.model.DetalleIngreso;
import pe.edu.utp.bodega_rb_api.repository.DetalleIngresoRepository;

@ExtendWith(MockitoExtension.class)
class DetalleIngresoServiceImplTest {

  @Mock
  private DetalleIngresoRepository detalleIngresoRepository;

  @InjectMocks
  private DetalleIngresoServiceImpl detalleIngresoService;

  @Test
  void findAll_DeberiaRetornarListaDeDetallesIngreso() {
    DetalleIngreso detalle1 = new DetalleIngreso();
    detalle1.setId(1);
    DetalleIngreso detalle2 = new DetalleIngreso();
    detalle2.setId(2);
    when(detalleIngresoRepository.findAll()).thenReturn(Arrays.asList(detalle1, detalle2));

    List<DetalleIngreso> resultado = detalleIngresoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarDetalleIngreso_CuandoExiste() {
    Integer id = 1;
    DetalleIngreso detalle = new DetalleIngreso();
    detalle.setId(id);
    when(detalleIngresoRepository.findById(id)).thenReturn(Optional.of(detalle));

    Optional<DetalleIngreso> resultado = detalleIngresoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarDetalleIngreso() {
    DetalleIngreso nuevoDetalle = new DetalleIngreso();
    DetalleIngreso detalleGuardado = new DetalleIngreso();
    detalleGuardado.setId(1);

    when(detalleIngresoRepository.save(any(DetalleIngreso.class))).thenReturn(detalleGuardado);

    DetalleIngreso resultado = detalleIngresoService.save(nuevoDetalle);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(detalleIngresoRepository).save(any(DetalleIngreso.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    detalleIngresoService.deleteById(id);
    verify(detalleIngresoRepository).deleteById(id);
  }
}
