package pe.edu.utp.bodega_rb_api.service;

import java.util.Optional;

import pe.edu.utp.bodega_rb_api.dto.ResumenCajaDTO;
import pe.edu.utp.bodega_rb_api.model.Caja;

public interface CajaService extends ApiService<Caja> {
  Optional<Caja> findCajaAbierta();

  ResumenCajaDTO getResumenCaja(Integer cajaId);

  Caja cerrarCaja(Caja caja);
}
