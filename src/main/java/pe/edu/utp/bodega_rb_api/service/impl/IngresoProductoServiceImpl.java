package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.IngresoProducto;
import pe.edu.utp.bodega_rb_api.repository.IngresoProductoRepository;
import pe.edu.utp.bodega_rb_api.service.IngresoProductoService;

@Service
public class IngresoProductoServiceImpl implements IngresoProductoService {
  @Autowired
  private IngresoProductoRepository repository;

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
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}