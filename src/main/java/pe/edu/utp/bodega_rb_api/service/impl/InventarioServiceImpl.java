package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Inventario;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;
import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.InventarioRepository;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;
import pe.edu.utp.bodega_rb_api.service.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {
  @Autowired
  private InventarioRepository repository;
  @Autowired
  private ProductoRepository productoRepository;

  @Override
  public List<Inventario> findAll() {
    return repository.findAll();
  }

  @Override
  public List<Inventario> findByEstado(Boolean estado) {
    return repository.findByEstado(estado);
  }

  @Override
  public Optional<Inventario> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Inventario save(Inventario inventario) {
    List<Producto> productos = productoRepository.findByCategoria_Id(inventario.getCategoria().getId());

    for (Producto producto : productos) {
      InventarioDetalle inventarioDetalle = new InventarioDetalle();

      inventarioDetalle.setProducto(producto);
      inventarioDetalle.setStockSistema(producto.getStock());
      inventarioDetalle.setDiferencia(producto.getStock());

      inventario.addInventarioDetalle(inventarioDetalle);
    }

    return repository.save(inventario);
  }

  @Override
  public Optional<Inventario> updateById(Integer id, Inventario inventario) {
    return this.findById(id)
        .map(i -> {
          inventario.setId(id);
          inventario.setInventarioDetalles(i.getInventarioDetalles());

          return repository.save(inventario);
        });
  }

  @Override
  public Optional<Inventario> addAsistenteAlmacen(Integer id, Empleado asistenteAlamcen) {
    return this.findById(id)
        .map(inventario -> {
          inventario.addAsistenteAlmacen(asistenteAlamcen);

          return repository.save(inventario);
        });
  }

  @Override
  public Optional<Inventario> cerrarInventario(Integer id) {
    return this.findById(id)
        .map(inventario -> {
          inventario.setFechaInventario(LocalDate.now());
          inventario.setEstado(false);

          for (InventarioDetalle inventarioDetalle : inventario.getInventarioDetalles()) {
            Producto producto = productoRepository.findById(inventarioDetalle.getProducto().getId()).get();

            producto.setStock(inventarioDetalle.getStockFisico());

            productoRepository.save(producto);
          }

          return repository.save(inventario);
        });
  }

  @Override
  public void deleteById(Integer id) {
    this.findById(id)
        .orElseThrow(() -> new RuntimeException("Inventario con id: " + id + " no encontrado"));

    repository.deleteById(id);
  }
}