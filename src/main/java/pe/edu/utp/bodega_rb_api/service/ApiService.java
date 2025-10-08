package pe.edu.utp.bodega_rb_api.service;

import java.util.List;
import java.util.Optional;

public interface ApiService<T> {
  List<T> findAll();

  Optional<T> findById(Integer id);

  T save(T entity);

  void deleteById(Integer id);
}
