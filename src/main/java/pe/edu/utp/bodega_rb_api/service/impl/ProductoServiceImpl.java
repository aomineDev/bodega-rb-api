package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;
import pe.edu.utp.bodega_rb_api.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

  @Override
  public List<Producto> findAll() {
    return productoRepository.findAll();
  }

  @Override
  public Optional<Producto> findById(Integer id) {
    return productoRepository.findById(id);
  }

  @Override
  public List<Producto> findByCategoria_Id(Integer id) {
    return productoRepository.findByCategoria_Id(id);
  }

  @Override
  public Producto save(Producto entity) {
    return productoRepository.save(entity);
  }

  @Override
  public Optional<Producto> updateStockById(Integer id, Double stock) {
    return productoRepository.findById(id).map(producto -> {
      producto.setStock(stock);
      return productoRepository.save(producto);
    });
  }

  @Override
  public Optional<Producto> updateOfferById(Integer id, Double precio, LocalDate fechaFin) {
    return productoRepository.findById(id).map(producto -> {
      producto.setPrecioPromocion(precio);
      producto.setInicioPromocion(LocalDate.now());
      producto.setFinPromocion(fechaFin);
      return productoRepository.save(producto);
    });
  }

  @Override
  public void deleteById(Integer id) {
    productoRepository.deleteById(id);
  }

  @Override
  public void descontarStock(Integer id, Double cantidad) {
    productoRepository.descontarStock(id, cantidad);
  }

}
