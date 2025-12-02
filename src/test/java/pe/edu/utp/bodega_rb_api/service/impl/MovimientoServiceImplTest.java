package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.model.Movimiento;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;
import pe.edu.utp.bodega_rb_api.repository.MovimientoRepository;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceImplTest {

  @Mock
  private MovimientoRepository movimientoRepository;

  @Mock
  private CajaRepository cajaRepository;

  @InjectMocks
  private MovimientoServiceImpl movimientoService;

  @Test
  void findAll_DeberiaRetornarListaDeMovimientos() {
    Movimiento mov1 = new Movimiento();
    mov1.setId(1);
    Movimiento mov2 = new Movimiento();
    mov2.setId(2);
    when(movimientoRepository.findAll()).thenReturn(Arrays.asList(mov1, mov2));

    List<Movimiento> resultado = movimientoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarMovimiento_CuandoExiste() {
    Integer id = 1;
    Movimiento mov = new Movimiento();
    mov.setId(id);
    when(movimientoRepository.findById(id)).thenReturn(Optional.of(mov));

    Optional<Movimiento> resultado = movimientoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarIngreso_YActualizarCaja() {
    Caja caja = new Caja();
    caja.setId(1);
    caja.setSaldoActual(100.0);

    Movimiento movimiento = new Movimiento();
    movimiento.setCaja(caja);
    movimiento.setMonto(50.0);
    movimiento.setTipo("INGRESO");

    when(cajaRepository.findById(1)).thenReturn(Optional.of(caja));
    when(cajaRepository.save(any(Caja.class))).thenAnswer(invocation -> invocation.getArgument(0));
    when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> {
      Movimiento m = invocation.getArgument(0);
      m.setId(1);
      return m;
    });

    Movimiento resultado = movimientoService.save(movimiento);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getFecha()).isNotNull();
    assertThat(resultado.getHora()).isNotNull();
    assertThat(caja.getSaldoActual()).isEqualTo(150.0);
    verify(cajaRepository).save(caja);
    verify(movimientoRepository).save(any(Movimiento.class));
  }

  @Test
  void save_DeberiaGuardarRetiro_YActualizarCaja() {
    Caja caja = new Caja();
    caja.setId(1);
    caja.setSaldoActual(100.0);

    Movimiento movimiento = new Movimiento();
    movimiento.setCaja(caja);
    movimiento.setMonto(50.0);
    movimiento.setTipo("RETIRO");

    when(cajaRepository.findById(1)).thenReturn(Optional.of(caja));
    when(cajaRepository.save(any(Caja.class))).thenAnswer(invocation -> invocation.getArgument(0));
    when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(invocation -> {
      Movimiento m = invocation.getArgument(0);
      m.setId(1);
      return m;
    });

    Movimiento resultado = movimientoService.save(movimiento);

    assertThat(resultado).isNotNull();
    assertThat(caja.getSaldoActual()).isEqualTo(50.0);
    verify(cajaRepository).save(caja);
  }

  @Test
  void save_DeberiaLanzarExcepcion_CuandoSaldoInsuficiente() {
    Caja caja = new Caja();
    caja.setId(1);
    caja.setSaldoActual(10.0);

    Movimiento movimiento = new Movimiento();
    movimiento.setCaja(caja);
    movimiento.setMonto(50.0);
    movimiento.setTipo("RETIRO");

    when(cajaRepository.findById(1)).thenReturn(Optional.of(caja));

    assertThatThrownBy(() -> movimientoService.save(movimiento))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("No hay suficiente saldo en caja para esta operación");
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    movimientoService.deleteById(id);
    verify(movimientoRepository).deleteById(id);
  }

  @Test
  void findByCaja_DeberiaRetornarListaDeMovimientos() {
    Integer cajaId = 1;
    Movimiento mov1 = new Movimiento();
    when(movimientoRepository.findByCajaIdOrderByFechaAscHoraAsc(cajaId)).thenReturn(Arrays.asList(mov1));

    List<Movimiento> resultado = movimientoService.findByCaja(cajaId);

    assertThat(resultado).hasSize(1);
  }
}
