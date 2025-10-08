package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.ClienteJuridico;
import pe.edu.utp.bodega_rb_api.repository.ClienteJuridicoRepository;
import pe.edu.utp.bodega_rb_api.service.ClienteJuridicoService;

@Service
public class ClienteJuridicoServiceImpl implements ClienteJuridicoService {
  @Autowired
  private ClienteJuridicoRepository repository;

  @Override
  public List<ClienteJuridico> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<ClienteJuridico> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public ClienteJuridico save(ClienteJuridico entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
