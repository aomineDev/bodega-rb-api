package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.bodega_rb_api.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
