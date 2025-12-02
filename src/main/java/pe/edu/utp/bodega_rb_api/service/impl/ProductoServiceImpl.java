package pe.edu.utp.bodega_rb_api.service.impl;

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
  public void deleteById(Integer id) {
    productoRepository.deleteById(id);
  }

  @Override
  public void descontarStock(Integer id, Double cantidad) {
    productoRepository.descontarStock(id, cantidad);
  }

  @Override
  public void agregarStock(Integer id, Double cantidad) {
    productoRepository.agregarStock(id, cantidad);
  }

}
