package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.bodega_rb_api.dto.InventarioDetalleDTO;
import pe.edu.utp.bodega_rb_api.model.InventarioDetalle;
import pe.edu.utp.bodega_rb_api.service.InventarioDetalleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/inventario-detalles")
public class InventarioDetalleController {

  @Autowired
  private InventarioDetalleService inventarioDetalleService;

  @GetMapping
  public ResponseEntity<List<InventarioDetalle>> getAll() {
    return ResponseEntity.ok(inventarioDetalleService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<InventarioDetalle> getById(@PathVariable Integer id) {
    return inventarioDetalleService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<InventarioDetalle> create(@RequestBody InventarioDetalle inventarioDetalle) {
    return ResponseEntity.status(HttpStatus.CREATED).body(inventarioDetalleService.save(inventarioDetalle));
  }

  @PutMapping("/{id}")
  public ResponseEntity<InventarioDetalle> update(@PathVariable Integer id,
      @RequestBody InventarioDetalle inventarioDetalle) {
    return inventarioDetalleService.updateById(id, inventarioDetalle)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/stock/add/{id}")
  public ResponseEntity<InventarioDetalle> addStock(@PathVariable Integer id,
      @RequestBody InventarioDetalleDTO inventarioDetalleDTO) {
    return inventarioDetalleService.addStockById(id, inventarioDetalleDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/stock/{id}")
  public ResponseEntity<InventarioDetalle> updateStock(@PathVariable Integer id,
      @RequestBody InventarioDetalleDTO inventarioDetalleDTO) {
    return inventarioDetalleService.updateStockById(id, inventarioDetalleDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/observaciones/{id}")
  public ResponseEntity<InventarioDetalle> updateObservaciones(@PathVariable Integer id,
      @RequestBody InventarioDetalleDTO inventarioDetalleDTO) {
    return inventarioDetalleService.updateObservacionesById(id, inventarioDetalleDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    inventarioDetalleService.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
