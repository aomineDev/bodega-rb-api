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

import pe.edu.utp.bodega_rb_api.model.ClienteNatural;
import pe.edu.utp.bodega_rb_api.service.ClienteNaturalService;

@RestController
@RequestMapping("/api/clientes-naturales")
public class ClienteNaturalController {
  @Autowired
  ClienteNaturalService service;

  @GetMapping
  public ResponseEntity<List<ClienteNatural>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteNatural> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ClienteNatural> save(@RequestBody ClienteNatural clienteNatural) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteNatural));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteNatural> update(@PathVariable Integer id, @RequestBody ClienteNatural clienteNatural) {
    clienteNatural.setId(id);
    return ResponseEntity.ok(service.save(clienteNatural));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
