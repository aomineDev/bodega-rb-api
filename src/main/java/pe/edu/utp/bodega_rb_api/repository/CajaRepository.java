package pe.edu.utp.bodega_rb_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.Caja;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Integer> {
  Optional<Caja> findByEstadoTrue();
}
