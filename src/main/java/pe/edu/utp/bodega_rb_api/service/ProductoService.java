package pe.edu.utp.bodega_rb_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import pe.edu.utp.bodega_rb_api.model.Producto;

public interface ProductoService extends ApiService<Producto> {
  List<Producto> findByCategoria_Id(Integer id);

  Optional<Producto> updateOfferById(Integer id, Double precio, LocalDate fechaFin);

  Optional<Producto> updateStockById(Integer id, Double stock);

  void descontarStock(Integer id, Double cantidad);
}
