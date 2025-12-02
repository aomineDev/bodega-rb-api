package pe.edu.utp.bodega_rb_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;
import pe.edu.utp.bodega_rb_api.model.ProximoAVencer;
import pe.edu.utp.bodega_rb_api.repository.ProximoAVencerRepository;
import pe.edu.utp.bodega_rb_api.service.ProductoService;
import pe.edu.utp.bodega_rb_api.service.ProximoAVencerService;

@Service
public class ProximoAVencerServiceImpl implements ProximoAVencerService {
  @Autowired
  private ProximoAVencerRepository proximosAVencerRepository;

  @Autowired
  private ProductoService productoService;

  @Override
  public List<ProximoAVencer> findAll() {
    return proximosAVencerRepository.findAll();
  }

  @Override
  public Optional<ProximoAVencer> findById(Integer id) {
    return proximosAVencerRepository.findById(id);
  }

  @Override
  public ProximoAVencer save(ProximoAVencer proximosAVencer) {
    return proximosAVencerRepository.save(proximosAVencer);
  }

  @Override
  public void handle2x1(Integer id) {
    ProximoAVencer proximosAVencer = this.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto con id: " + id + " no encontrado"));

    Double halfStock = proximosAVencer.getProducto().getUnidadMedida().equals("unidad")
        ? Math.floor(proximosAVencer.getCantidad() / 2)
        : (proximosAVencer.getCantidad() / 2);
    Double newStock = proximosAVencer.getProducto().getStock() - halfStock;

    this.productoService.updateStockById(proximosAVencer.getProducto().getId(), newStock);

    this.deleteById(id);
  }

  @Override
  public void handleOfferPrice(Integer id, Double precio) {
    ProximoAVencer proximosAVencer = this.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto con id: " + id + " no encontrado"));

    this.productoService.updateOfferById(proximosAVencer.getProducto().getId(), precio,
        proximosAVencer.getFechaVencimiento());

    this.deleteById(id);
  }

  @Override
  public void deleteById(Integer id) {
    this.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto con id: " + id + " no encontrado"));

    proximosAVencerRepository.deleteById(id);
  }

}
