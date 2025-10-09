package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Proveedor;
import pe.edu.utp.bodega_rb_api.repository.ProveedorRepository;
import pe.edu.utp.bodega_rb_api.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {

  @Autowired
  private ProveedorRepository proveedorRepository;

  @Override
  public List<Proveedor> findAll() {
    return proveedorRepository.findAll();
  }

  @Override
  public Optional<Proveedor> findById(Integer id) {
    return proveedorRepository.findById(id);
  }

  @Override
  public Proveedor save(Proveedor entity) {
    return proveedorRepository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    proveedorRepository.deleteById(id);
  }

}
