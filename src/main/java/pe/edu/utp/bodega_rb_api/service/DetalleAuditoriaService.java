package pe.edu.utp.bodega_rb_api.service;

import pe.edu.utp.bodega_rb_api.model.DetalleAuditoria;
import java.util.List;

public interface DetalleAuditoriaService {
    DetalleAuditoria save(DetalleAuditoria detalleAuditoria);
    List<DetalleAuditoria> findAll();
    DetalleAuditoria findById(Integer id);
    void deleteById(Integer id);
}