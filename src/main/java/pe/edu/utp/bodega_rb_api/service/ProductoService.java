package pe.edu.utp.bodega_rb_api.service;

import java.util.List;

import pe.edu.utp.bodega_rb_api.model.Producto;

public interface ProductoService extends ApiService<Producto> {
  List<Producto> findByCategoria_Id(Integer id);

}
