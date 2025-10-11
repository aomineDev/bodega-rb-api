package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.AuditoriaInventario;
import pe.edu.utp.bodega_rb_api.repository.AuditoriaInventarioRepository;
import pe.edu.utp.bodega_rb_api.service.AuditoriaInventarioService;

@Service
public class AuditoriaInventarioImpl implements AuditoriaInventarioService {
  @Autowired
  private AuditoriaInventarioRepository repository;

  @Override
  public List<AuditoriaInventario> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<AuditoriaInventario> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public AuditoriaInventario save(AuditoriaInventario entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}