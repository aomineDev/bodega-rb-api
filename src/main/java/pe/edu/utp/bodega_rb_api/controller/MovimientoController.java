package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.model.Movimiento;
import pe.edu.utp.bodega_rb_api.service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
  @Autowired
  private MovimientoService service;

  @GetMapping
  public ResponseEntity<List<Movimiento>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movimiento> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(movimiento));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movimiento> update(@PathVariable Integer id, @RequestBody Movimiento movimiento) {
    movimiento.setId(id);
    return ResponseEntity.ok(service.save(movimiento));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/caja/{cajaId}")
  public ResponseEntity<List<Movimiento>> findByCaja(@PathVariable Integer cajaId) {
    return ResponseEntity.ok(service.findByCaja(cajaId));
  }

}
