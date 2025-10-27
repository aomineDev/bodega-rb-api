package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.bodega_rb_api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
  boolean existsByNombreRol(String nombreRol);

}
