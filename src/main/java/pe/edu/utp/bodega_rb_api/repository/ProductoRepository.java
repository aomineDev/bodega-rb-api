package pe.edu.utp.bodega_rb_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
  List<Producto> findByCategoria_Id(Integer id);

  List<Producto> findByStockLessThan(Double stock);
}