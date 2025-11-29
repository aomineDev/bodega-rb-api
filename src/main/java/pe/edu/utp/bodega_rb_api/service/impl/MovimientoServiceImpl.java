package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Movimiento;
import pe.edu.utp.bodega_rb_api.repository.MovimientoRepository;
import pe.edu.utp.bodega_rb_api.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {
  @Autowired
  private MovimientoRepository repository;

  @Override
  public List<Movimiento> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Movimiento> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Movimiento save(Movimiento entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}