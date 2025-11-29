package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Arqueo;
import pe.edu.utp.bodega_rb_api.repository.ArqueoRepository;
import pe.edu.utp.bodega_rb_api.service.ArqueoService;

@Service
public class ArqueoServiceImpl implements ArqueoService {
  @Autowired
  private ArqueoRepository repository;

  @Override
  public List<Arqueo> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Arqueo> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Arqueo save(Arqueo entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}