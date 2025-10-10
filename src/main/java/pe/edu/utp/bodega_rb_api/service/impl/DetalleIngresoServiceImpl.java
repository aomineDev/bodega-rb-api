package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.DetalleIngreso;
import pe.edu.utp.bodega_rb_api.repository.DetalleIngresoRepository;
import pe.edu.utp.bodega_rb_api.service.DetalleIngresoService;

@Service
public class DetalleIngresoServiceImpl implements DetalleIngresoService {
  @Autowired
  private DetalleIngresoRepository repository;

  @Override
  public List<DetalleIngreso> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<DetalleIngreso> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public DetalleIngreso save(DetalleIngreso entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}