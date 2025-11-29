package pe.edu.utp.bodega_rb_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
  List<Movimiento> findByCajaIdOrderByFechaAscHoraAsc(Integer cajaId);

  // SUM de montos por tipo de movimiento en una caja
  @Query("SELECT COALESCE(SUM(m.monto),0) FROM Movimiento m WHERE m.caja.id = :cajaId AND m.tipo = :tipo")
  Double sumMontoByCajaIdAndTipo(@Param("cajaId") Integer cajaId, @Param("tipo") String tipo);

  // Listado de movimientos de tipo VENTA (por si quieres detalle)
  List<Movimiento> findByCajaIdAndTipo(Integer cajaId, String tipo);
}
