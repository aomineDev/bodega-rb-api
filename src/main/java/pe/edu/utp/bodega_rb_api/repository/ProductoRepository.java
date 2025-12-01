package pe.edu.utp.bodega_rb_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.edu.utp.bodega_rb_api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
  List<Producto> findByCategoria_Id(Integer id);

  List<Producto> findByStockLessThan(Double stock);

  @Transactional
  @Modifying
  @Query("UPDATE Producto p SET p.stock = p.stock - :cantidad WHERE p.id = :id")
  void descontarStock(@Param("id") Integer id, @Param("cantidad") Double cantidad);

}