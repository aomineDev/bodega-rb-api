package pe.edu.utp.bodega_rb_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.Comprobante;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

  // Listar todos los comprobantes de una caja
  @Query("SELECT c FROM Comprobante c WHERE c.cajero.id = :cajaId")
  List<Comprobante> findByCajaId(@Param("cajaId") Integer cajaId);

  @Query("SELECT COALESCE(SUM(c.precioTotal), 0) FROM Comprobante c " +
      "WHERE c.caja.id = :cajaId AND c.tipoPago = :tipo")
  Double sumByCajaIdAndTipo(@Param("cajaId") Integer cajaId, @Param("tipo") String tipo);

  @Query("SELECT COUNT(c) FROM Comprobante c WHERE c.fecha = CURRENT_DATE AND c.estado = true")
  Long dailySales();

  @Query("SELECT COALESCE(SUM(c.precioTotal), 0.0) FROM Comprobante c WHERE c.fecha = CURRENT_DATE AND c.estado = true")
  Double dailyRevenue();

  @Query("SELECT c.fecha, COALESCE(SUM(c.precioTotal), 0.0) " +
      "FROM Comprobante c " +
      "WHERE c.fecha >= :fechaInicio AND c.estado = true " +
      "GROUP BY c.fecha " +
      "ORDER BY c.fecha ASC")
  List<Object[]> dailyRevenues(@Param("fechaInicio") LocalDate fechaInicio);

  @Query("SELECT dv.producto.nombre, SUM(dv.cantidad) as totalVendido " +
      "FROM Comprobante c JOIN c.detalleVentas dv " +
      "WHERE c.fecha >= :fechaInicio AND c.estado = true " +
      "GROUP BY  dv.producto.nombre " +
      "ORDER BY totalVendido DESC")
  List<Object[]> bestSellingProducts(@Param("fechaInicio") LocalDate fechaInicio, Pageable pageable);

  @Query("SELECT dv.producto.categoria.nombre, SUM(dv.cantidad) as totalVendido " +
      "FROM Comprobante c JOIN c.detalleVentas dv " +
      "WHERE c.fecha >= :fechaInicio AND c.estado = true " +
      "GROUP BY dv.producto.categoria.nombre " +
      "ORDER BY totalVendido DESC")
  List<Object[]> bestSellingCategories(@Param("fechaInicio") LocalDate fechaInicio);

}
