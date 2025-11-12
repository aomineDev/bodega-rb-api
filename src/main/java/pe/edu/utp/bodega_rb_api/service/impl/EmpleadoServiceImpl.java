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
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmpleadoRepository empleadoRepository;

  @Override
  public List<Empleado> findAll() {
    return empleadoRepository.findAll();
  }

  @Override
  public Optional<Empleado> findById(Integer id) {
    return empleadoRepository.findById(id);
  }

  @Override
  public List<Empleado> findByRol_Nombre(String dni) {
    return empleadoRepository.findByRol_Nombre(dni);
  }

  @Override
  public Optional<Empleado> findByDni(String dni) {
    return empleadoRepository.findByDni(dni);
  }

  @Override
  public Empleado save(Empleado entity) {
    if (entity.getId() != null) {
      Optional<Empleado> existingEmpleadoOpt = empleadoRepository.findById(entity.getId());
      if (existingEmpleadoOpt.isPresent()) {
        Empleado existingEmpleado = existingEmpleadoOpt.get();

        if (entity.getClave() != null && !entity.getClave().isEmpty()) {
          entity.setClave(passwordEncoder.encode(entity.getClave()));
        } else {
          entity.setClave(existingEmpleado.getClave());
        }
      }
    } else {
      // Es un nuevo registro
      if (entity.getClave() != null && !entity.getClave().isEmpty()) {
        entity.setClave(passwordEncoder.encode(entity.getClave()));
      }
    }

    return empleadoRepository.save(entity);
  }

  @Override
  public Empleado updatePassword(Integer id, String currentPassword, String newPassword) {
    Empleado empleado = empleadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

    if (!passwordEncoder.matches(currentPassword, empleado.getClave())) {
      throw new RuntimeException("Contraseña actual incorrecta");
    }

    empleado.setClave(passwordEncoder.encode(newPassword));
    return empleadoRepository.save(empleado);
  }

  @Override
  public void deleteById(Integer id) {
    empleadoRepository.deleteById(id);
  }

}
