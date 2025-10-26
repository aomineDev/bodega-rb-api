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

import pe.edu.utp.bodega_rb_api.model.Proveedor;
import pe.edu.utp.bodega_rb_api.service.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
  @Autowired
  private ProveedorService service;

  @GetMapping
  public ResponseEntity<List<Proveedor>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Proveedor> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(proveedor));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Proveedor> update(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
    proveedor.setId(id);
    return ResponseEntity.ok(service.save(proveedor));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
