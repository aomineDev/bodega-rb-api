package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;

@Repository
public interface InventarioDetalleRepository extends JpaRepository<InventarioDetalle, Integer> {

}
