package pe.edu.utp.bodega_rb_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.bodega_rb_api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
