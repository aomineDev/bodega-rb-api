package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
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

import pe.edu.utp.bodega_rb_api.dto.ResumenCajaDTO;
import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;
import pe.edu.utp.bodega_rb_api.repository.ComprobanteRepository;
import pe.edu.utp.bodega_rb_api.repository.MovimientoRepository;

@ExtendWith(MockitoExtension.class)
class CajaServiceImplTest {

  @Mock
  private CajaRepository cajaRepository;

  @Mock
  private MovimientoRepository movimientoRepository;

  @Mock
  private ComprobanteRepository comprobanteRepository;

  @InjectMocks
  private CajaServiceImpl cajaService;

  @Test
  void findAll_DeberiaRetornarListaDeCajas() {
    Caja caja1 = new Caja();
    caja1.setId(1);
    Caja caja2 = new Caja();
    caja2.setId(2);
    when(cajaRepository.findAll()).thenReturn(Arrays.asList(caja1, caja2));

    List<Caja> resultado = cajaService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarCaja_CuandoExiste() {
    Integer id = 1;
    Caja caja = new Caja();
    caja.setId(id);
    when(cajaRepository.findById(id)).thenReturn(Optional.of(caja));

    Optional<Caja> resultado = cajaService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarCaja_YEstablecerValoresIniciales() {
    Caja nuevaCaja = new Caja();
    nuevaCaja.setMontoApertura(500.0);

    Caja cajaGuardada = new Caja();
    cajaGuardada.setId(1);
    cajaGuardada.setMontoApertura(500.0);
    cajaGuardada.setSaldoActual(500.0);
    cajaGuardada.setEstado(true);
    cajaGuardada.setFechaApertura(LocalDate.now());
    cajaGuardada.setHoraApertura(LocalTime.now());

    when(cajaRepository.save(any(Caja.class))).thenAnswer(invocation -> {
      Caja c = invocation.getArgument(0);
      c.setId(1); // Simulate DB ID generation
      return c;
    });

    Caja resultado = cajaService.save(nuevaCaja);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getSaldoActual()).isEqualTo(500.0);
    assertThat(resultado.getEstado()).isTrue();
    assertThat(resultado.getFechaApertura()).isNotNull();
    assertThat(resultado.getHoraApertura()).isNotNull();
    verify(cajaRepository).save(any(Caja.class));
  }

  @Test
  void cerrarCaja_DeberiaCerrarCajaCorrectamente() {
    Integer id = 1;
    Caja cajaAbierta = new Caja();
    cajaAbierta.setId(id);
    cajaAbierta.setEstado(true);

    Caja cajaParam = new Caja();
    cajaParam.setId(id);
    cajaParam.setMontoCierre(1000.0);
    // cajaParam.setEmpleadoCierre(...);

    when(cajaRepository.findById(id)).thenReturn(Optional.of(cajaAbierta));
    when(cajaRepository.save(any(Caja.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Caja resultado = cajaService.cerrarCaja(cajaParam);

    assertThat(resultado.getEstado()).isFalse();
    assertThat(resultado.getMontoCierre()).isEqualTo(1000.0);
    assertThat(resultado.getFechaCierre()).isNotNull();
    assertThat(resultado.getHoraCierre()).isNotNull();
  }

  @Test
  void findCajaAbierta_DeberiaRetornarCajaAbierta() {
    Caja caja = new Caja();
    caja.setEstado(true);
    when(cajaRepository.findByEstadoTrue()).thenReturn(Optional.of(caja));

    Optional<Caja> resultado = cajaService.findCajaAbierta();

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getEstado()).isTrue();
  }

  @Test
  void getResumenCaja_DeberiaCalcularTotalesCorrectamente() {
    Integer cajaId = 1;
    Caja caja = new Caja();
    caja.setId(cajaId);
    caja.setMontoApertura(100.0);
    caja.setSaldoActual(500.0);

    when(cajaRepository.findById(cajaId)).thenReturn(Optional.of(caja));

    // Mocking repository sums
    // Double ventasEfectivo = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId,
    // "VENTA");
    when(movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "VENTA")).thenReturn(200.0);

    // Double ventasTarjeta = comprobanteRepository.sumByCajaIdAndTipo(cajaId,
    // "Tarjeta");
    when(comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Tarjeta")).thenReturn(50.0);
    when(comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Yape")).thenReturn(20.0);
    when(comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Plin")).thenReturn(30.0);

    // Double ingresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId,
    // "INGRESO");
    when(movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "INGRESO")).thenReturn(10.0);
    when(movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "VUELTO")).thenReturn(5.0);
    when(movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "RETIRO")).thenReturn(15.0);

    ResumenCajaDTO resumen = cajaService.getResumenCaja(cajaId);

    assertThat(resumen).isNotNull();
    assertThat(resumen.getApertura()).isEqualTo(100.0);
    assertThat(resumen.getVentasEfectivo()).isEqualTo(200.0);
    assertThat(resumen.getVentasTarjeta()).isEqualTo(50.0);
    assertThat(resumen.getVentasYape()).isEqualTo(20.0);
    assertThat(resumen.getVentasPlin()).isEqualTo(30.0);

    // ventasDelDia = 200 + 50 + 20 + 30 = 300
    assertThat(resumen.getVentasDelDia()).isEqualTo(300.0);

    // ventasElectronicas = 50 + 20 + 30 = 100
    assertThat(resumen.getVentasElectronicas()).isEqualTo(100.0);

    assertThat(resumen.getIngresos()).isEqualTo(10.0);
    assertThat(resumen.getEgresosVuelto()).isEqualTo(5.0);
    assertThat(resumen.getEgresosRetiro()).isEqualTo(15.0);

    // ingresosTotales = 100 (apertura) + 200 (ventas efectivo) + 10 (ingresos) =
    // 310
    assertThat(resumen.getIngresosTotales()).isEqualTo(310.0);

    // egresosTotales = 15 (retiro) + 5 (vuelto) = 20
    assertThat(resumen.getEgresosTotales()).isEqualTo(20.0);

    assertThat(resumen.getSaldoCalculado()).isEqualTo(500.0);
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    cajaService.deleteById(id);
    verify(cajaRepository).deleteById(id);
  }
}
