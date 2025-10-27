package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.repository.EmpleadoRepository;
import pe.edu.utp.bodega_rb_api.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  private EmpleadoRepository empleadoRepository;

  EmpleadoServiceImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<Empleado> findAll() {
    return empleadoRepository.findAll();
  }

  @Override
  public Optional<Empleado> findById(Integer id) {
    return empleadoRepository.findById(id);
  }

  @Override
  public Empleado save(Empleado entity) {

    if (entity.getClave() != null && !entity.getClave().isEmpty()) {
      entity.setClave(passwordEncoder.encode(entity.getClave()));
    }
    return empleadoRepository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    empleadoRepository.deleteById(id);
  }

}
