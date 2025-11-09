package pe.edu.utp.bodega_rb_api.service;

import java.util.List;
import java.util.Optional;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Inventario;

public interface InventarioService extends ApiService<Inventario> {
  public List<Inventario> findByEstado(Boolean estado);

  public Optional<Inventario> updateById(Integer Id, Inventario inventario);

  public Optional<Inventario> addAsistenteAlmacen(Integer id, Empleado asistenteAlamcen);

  public Optional<Inventario> cerrarInventario(Integer id);

}