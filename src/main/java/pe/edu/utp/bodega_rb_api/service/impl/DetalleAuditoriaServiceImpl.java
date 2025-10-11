package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.DetalleAuditoria;
import pe.edu.utp.bodega_rb_api.repository.DetalleAuditoriaRepository;
import pe.edu.utp.bodega_rb_api.service.DetalleAuditoriaService;

@Service
public class DetalleAuditoriaServiceImpl implements DetalleAuditoriaService {
  @Autowired
  private DetalleAuditoriaRepository repository;

  @Override
  public List<DetalleAuditoria> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<DetalleAuditoria> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public DetalleAuditoria save(DetalleAuditoria entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}