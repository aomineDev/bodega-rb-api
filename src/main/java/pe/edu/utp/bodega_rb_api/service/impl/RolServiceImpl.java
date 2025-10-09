package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;
import pe.edu.utp.bodega_rb_api.service.RolService;

@Service
public class RolServiceImpl implements RolService {
  @Autowired
  private RolRepository rolRepository;

  @Override
  public List<Rol> findAll() {
    return rolRepository.findAll();

  }

  @Override
  public Optional<Rol> findById(Integer id) {

    return rolRepository.findById(id);
  }

  @Override
  public Rol save(Rol entity) {
    return rolRepository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    rolRepository.deleteById(id);
  }

}
