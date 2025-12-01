package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.dto.ResumenCajaDTO;
import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;
import pe.edu.utp.bodega_rb_api.repository.ComprobanteRepository;
import pe.edu.utp.bodega_rb_api.repository.MovimientoRepository;
import pe.edu.utp.bodega_rb_api.service.CajaService;

@Service
public class CajaServiceImpl implements CajaService {
  @Autowired
  private CajaRepository repository;

  @Autowired
  private MovimientoRepository movimientoRepository;

  @Autowired
  private ComprobanteRepository comprobanteRepository;

  @Override
  public List<Caja> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Caja> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Caja save(Caja entity) {

    entity.setSaldoActual(entity.getMontoApertura());
    entity.setFechaApertura(LocalDate.now());
    entity.setHoraApertura(LocalTime.now());
    entity.setEstado(true);

    return repository.save(entity);
  }

  @Override
  public Caja cerrarCaja(Caja cajaParam) {
    Caja caja = repository.findById(cajaParam.getId())
        .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

    caja.setFechaCierre(LocalDate.now());
    caja.setHoraCierre(LocalTime.now());
    caja.setEstado(false);
    caja.setMontoCierre(cajaParam.getMontoCierre());
    caja.setEmpleadoCierre(cajaParam.getEmpleadoCierre());

    return repository.save(caja);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<Caja> findCajaAbierta() {
    return repository.findByEstadoTrue();
  }

  @Override
  public ResumenCajaDTO getResumenCaja(Integer cajaId) {
    Caja caja = repository.findById(cajaId).orElseThrow(() -> new RuntimeException("Caja no encontrada"));

    Double ventasEfectivo = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "VENTA");

    Double ventasTarjeta = comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Tarjeta");
    Double ventasYape = comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Yape");
    Double ventasPlin = comprobanteRepository.sumByCajaIdAndTipo(cajaId, "Plin");
    Double ventasDelDia = ventasEfectivo + ventasPlin + ventasTarjeta + ventasYape;
    Double ventasElectronicas = ventasPlin + ventasTarjeta + ventasYape;

    Double ingresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "INGRESO");
    Double egresosVuelto = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "VUELTO");
    Double egresosRetiro = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "RETIRO");

    Double ingresosTotales = caja.getMontoApertura() + ventasEfectivo + ingresos;
    Double egresosTotales = egresosRetiro + egresosVuelto;

    return new ResumenCajaDTO(
        caja.getMontoApertura(),
        ventasEfectivo,
        ventasTarjeta,
        ventasYape,
        ventasPlin,
        ventasDelDia,
        ventasElectronicas,
        ingresos,
        egresosVuelto,
        egresosRetiro,
        ingresosTotales,
        egresosTotales,
        caja.getSaldoActual());
  }

}