package pe.edu.utp.bodega_rb_api.repository;

import java.util.List;

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

}
