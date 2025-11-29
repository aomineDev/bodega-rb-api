package pe.edu.utp.bodega_rb_api.service;

import java.util.List;

import pe.edu.utp.bodega_rb_api.model.Movimiento;

public interface MovimientoService extends ApiService<Movimiento> {
  List<Movimiento> findByCaja(Integer cajaId);
}
