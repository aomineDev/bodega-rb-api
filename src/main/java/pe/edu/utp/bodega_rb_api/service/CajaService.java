package pe.edu.utp.bodega_rb_api.service;

import java.util.Optional;

import pe.edu.utp.bodega_rb_api.model.Caja;

public interface CajaService extends ApiService<Caja> {
  Optional<Caja> findCajaAbierta();
}
