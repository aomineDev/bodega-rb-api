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

import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.service.CajaService;

@RestController
@RequestMapping("/api/caja")
public class CajaController {
  @Autowired
  private CajaService service;

  @GetMapping
  public ResponseEntity<List<Caja>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Caja> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Caja> save(@RequestBody Caja caja) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(caja));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Caja> update(@PathVariable Integer id, @RequestBody Caja caja) {
    caja.setId(id);
    return ResponseEntity.ok(service.save(caja));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/abierta")
  public ResponseEntity<Caja> getCajaAbierta() {
    return service.findCajaAbierta()
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.ok(null));
  }
}
