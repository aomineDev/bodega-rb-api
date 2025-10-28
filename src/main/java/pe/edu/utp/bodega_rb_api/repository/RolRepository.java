package pe.edu.utp.bodega_rb_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.bodega_rb_api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

  boolean existsByNombre(String nombre);

  Optional<Rol> findByNombre(String nombre);

}
