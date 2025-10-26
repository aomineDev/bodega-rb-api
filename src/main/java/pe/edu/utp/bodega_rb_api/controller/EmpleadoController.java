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

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
  @Autowired
  private EmpleadoService service;

  @GetMapping
  public ResponseEntity<List<Empleado>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Empleado> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Empleado> save(@RequestBody Empleado entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado entity) {
    entity.setId(id);
    return ResponseEntity.ok(service.save(entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
