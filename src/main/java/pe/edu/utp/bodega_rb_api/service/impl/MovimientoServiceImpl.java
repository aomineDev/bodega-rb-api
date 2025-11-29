package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.model.Movimiento;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;
import pe.edu.utp.bodega_rb_api.repository.MovimientoRepository;
import pe.edu.utp.bodega_rb_api.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {
  @Autowired
  private MovimientoRepository repository;

  @Autowired
  private CajaRepository cajaRepository;

  @Override
  public List<Movimiento> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Movimiento> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Movimiento save(Movimiento entity) {

    Caja caja = cajaRepository.findById(entity.getCaja().getId())
        .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

    // Validar monto
    if (entity.getMonto() == null) {
      throw new RuntimeException("El monto no puede ser nulo");
    }

    // Actualizar saldo según tipo
    switch (entity.getTipo()) {
      case "INGRESO":
      case "VENTA":
        // Aumenta el saldo
        caja.setSaldoActual(caja.getSaldoActual() + entity.getMonto());
        break;
      case "RETIRO":
      case "VUELTO":
        // Disminuye el saldo
        if (caja.getSaldoActual() < entity.getMonto()) {
          throw new RuntimeException("No hay suficiente saldo en caja para esta operación");
        }
        caja.setSaldoActual(caja.getSaldoActual() - entity.getMonto());
        break;
      default:
        throw new RuntimeException("Tipo de movimiento no válido");
    }

    // Guardar la caja actualizada
    cajaRepository.save(caja);

    entity.setFecha(LocalDate.now());
    entity.setHora(LocalTime.now());

    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public List<Movimiento> findByCaja(Integer cajaId) {
    return repository.findByCajaIdOrderByFechaAscHoraAsc(cajaId);
  }
}