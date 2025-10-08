package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Factura;
import pe.edu.utp.bodega_rb_api.repository.FacturaRepository;
import pe.edu.utp.bodega_rb_api.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {
  @Autowired
  private FacturaRepository repository;

  @Override
  public List<Factura> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Factura> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Factura save(Factura entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
