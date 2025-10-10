package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.bodega_rb_api.model.DetalleAuditoria;

public interface DetalleAuditoriaRepository extends JpaRepository<DetalleAuditoria, Integer> {
}