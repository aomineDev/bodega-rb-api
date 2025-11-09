package pe.edu.utp.bodega_rb_api.controller;

import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.service.ProductoService;
import pe.edu.utp.bodega_rb_api.util.enums.RolEnum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @GetMapping
  public ResponseEntity<List<Producto>> findAll() {
    return ResponseEntity.ok(productoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> findById(@PathVariable Integer id) {
    return productoService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMINISTRADOR')")
  public ResponseEntity<Producto> save(@RequestBody Producto entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto entity) {
    entity.setId(id);
    return ResponseEntity.ok(productoService.save(entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteByid(@PathVariable Integer id) {
    productoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
