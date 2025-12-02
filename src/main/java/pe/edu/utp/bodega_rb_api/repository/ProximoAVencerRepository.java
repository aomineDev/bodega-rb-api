package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.bodega_rb_api.model.ProximoAVencer;

@Repository
public interface ProximoAVencerRepository extends JpaRepository<ProximoAVencer, Integer> {

}
