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

import pe.edu.utp.bodega_rb_api.model.DetalleIngreso;
import pe.edu.utp.bodega_rb_api.service.DetalleIngresoService;

@RestController
@RequestMapping("/api/detalle-ingreso")
public class DetalleIngresoController {
  @Autowired
  private DetalleIngresoService service;

  @GetMapping
  public ResponseEntity<List<DetalleIngreso>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalleIngreso> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<DetalleIngreso> save(@RequestBody DetalleIngreso entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DetalleIngreso> update(@PathVariable Integer id, @RequestBody DetalleIngreso entity) {
    entity.setId(id);
    return ResponseEntity.ok(service.save(entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}