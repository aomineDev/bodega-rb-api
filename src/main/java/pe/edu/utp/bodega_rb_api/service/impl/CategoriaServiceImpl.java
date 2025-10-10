package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Categoria;
import pe.edu.utp.bodega_rb_api.repository.CategoriaRepository;
import pe.edu.utp.bodega_rb_api.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
  @Autowired
  private CategoriaRepository repository;

  @Override
  public List<Categoria> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Categoria> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Categoria save(Categoria entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}