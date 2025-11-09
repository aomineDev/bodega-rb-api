package pe.edu.utp.bodega_rb_api.service;

import java.util.Optional;

import pe.edu.utp.bodega_rb_api.dto.InventarioDetalleDTO;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;

public interface InventarioDetalleService extends ApiService<InventarioDetalle> {
  public Optional<InventarioDetalle> updateById(Integer id, InventarioDetalle inventarioDetalle);

  public Optional<InventarioDetalle> addStockById(Integer id, InventarioDetalleDTO inventarioDetalleDTO);

  public Optional<InventarioDetalle> updateStockById(Integer id, InventarioDetalleDTO inventarioDetalleDTO);

  public Optional<InventarioDetalle> updateObservacionesById(Integer id, InventarioDetalleDTO inventarioDetalleDTO);
}
