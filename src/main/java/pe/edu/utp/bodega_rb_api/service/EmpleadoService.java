package pe.edu.utp.bodega_rb_api.service;

import java.util.List;
import java.util.Optional;

import pe.edu.utp.bodega_rb_api.model.Empleado;

public interface EmpleadoService extends ApiService<Empleado> {
  List<Empleado> findByRol_Nombre(String rol);

  Optional<Empleado> findByDni(String dni);

  Empleado updatePassword(Integer id, String currentPassword, String newPassword);
}
