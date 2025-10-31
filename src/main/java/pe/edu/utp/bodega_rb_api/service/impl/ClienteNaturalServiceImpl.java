package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.ClienteNatural;
import pe.edu.utp.bodega_rb_api.repository.ClienteNaturalRepository;
import pe.edu.utp.bodega_rb_api.service.ClienteNaturalService;

@Service
public class ClienteNaturalServiceImpl implements ClienteNaturalService {
  @Autowired
  private ClienteNaturalRepository repository;

  @Override
  public List<ClienteNatural> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<ClienteNatural> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public ClienteNatural save(ClienteNatural entity) {
    if (entity.getId() == null) {
      entity.setFechaRegistro(LocalDate.now());
    }
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
