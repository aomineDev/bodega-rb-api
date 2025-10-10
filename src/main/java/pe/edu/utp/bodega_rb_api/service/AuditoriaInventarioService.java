package pe.edu.utp.bodega_rb_api.service;

import pe.edu.utp.bodega_rb_api.model.AuditoriaInventario;
import java.util.List;

public interface AuditoriaInventarioService {
    AuditoriaInventario save(AuditoriaInventario auditoriaInventario);
    List<AuditoriaInventario> findAll();
    AuditoriaInventario findById(Integer id);
    void deleteById(Integer id);
}