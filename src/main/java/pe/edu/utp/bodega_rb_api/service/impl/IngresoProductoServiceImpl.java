package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.IngresoProducto;
import pe.edu.utp.bodega_rb_api.repository.IngresoProductoRepository;
import pe.edu.utp.bodega_rb_api.service.IngresoProductoService;
import pe.edu.utp.bodega_rb_api.service.ProductoService;

@Service
public class IngresoProductoServiceImpl implements IngresoProductoService {
  @Autowired
  private IngresoProductoRepository repository;

  @Autowired
  private ProductoService productoService;

  @Override
  public List<IngresoProducto> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<IngresoProducto> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public IngresoProducto save(IngresoProducto entity) {
    IngresoProducto saved = repository.save(entity);

    // Solo sumar stock cuando se apruebe
    if ("Aprobado".equals(entity.getEstado())) {
      saved.getDetalleIngresos().forEach(det -> {
        productoService.agregarStock(
            det.getProducto().getId(),
            det.getCantidad());
      });
    }

    return saved;
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}