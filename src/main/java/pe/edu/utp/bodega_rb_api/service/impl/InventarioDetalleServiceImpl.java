package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.dto.InventarioDetalleDTO;
import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;
import pe.edu.utp.bodega_rb_api.repository.InventarioDetalleRepository;
import pe.edu.utp.bodega_rb_api.service.InventarioDetalleService;

@Service
public class InventarioDetalleServiceImpl implements InventarioDetalleService {
  @Autowired
  private InventarioDetalleRepository repository;

  @Override
  public List<InventarioDetalle> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<InventarioDetalle> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public InventarioDetalle save(InventarioDetalle inventarioDetalle) {
    return repository.save(inventarioDetalle);
  }

  @Override
  public Optional<InventarioDetalle> updateById(Integer id, InventarioDetalle inventarioDetalle) {
    return this.findById(id).map(i -> {
      inventarioDetalle.setId(id);

      return repository.save(inventarioDetalle);
    });
  }

  public Optional<InventarioDetalle> addStockById(Integer id, InventarioDetalleDTO inventarioDetalleDTO) {
    return this.findById(id).map(inventarioDetalle -> {
      inventarioDetalle.addStock(inventarioDetalleDTO.getStock());

      return repository.save(inventarioDetalle);
    });
  }

  @Override
  public Optional<InventarioDetalle> updateStockById(Integer id, InventarioDetalleDTO inventarioDetalleDTO) {
    return this.findById(id).map(inventarioDetalle -> {
      inventarioDetalle.setStockFisico(inventarioDetalleDTO.getStock());

      return repository.save(inventarioDetalle);
    });
  }

  @Override
  public Optional<InventarioDetalle> updateObservacionesById(Integer id, InventarioDetalleDTO inventarioDetalleDTO) {
    return this.findById(id).map(inventarioDetalle -> {
      inventarioDetalle.setObservaciones(inventarioDetalleDTO.getObservaciones());

      return repository.save(inventarioDetalle);
    });
  }

  @Override
  public void deleteById(Integer id) {
    this.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("InventarioDetalle con id: " + id + " no encontrado"));

    repository.deleteById(id);
  }

}
