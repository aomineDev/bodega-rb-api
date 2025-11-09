package pe.edu.utp.bodega_rb_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.bodega_rb_api.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
  List<Empleado> findByRol_Nombre(String rol);

  Optional<Empleado> findByDni(String dni);
}
