package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.model.ClienteJuridico;
import pe.edu.utp.bodega_rb_api.service.ClienteJuridicoService;

@RestController
@RequestMapping("/api/clientes-juridicos")
public class ClienteJuridicoController {
  @Autowired
  ClienteJuridicoService service;

  @GetMapping
  public ResponseEntity<List<ClienteJuridico>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteJuridico> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CAJERO')")
  public ResponseEntity<ClienteJuridico> save(@RequestBody ClienteJuridico clienteJuridico) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteJuridico));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CAJERO')")
  public ResponseEntity<ClienteJuridico> update(@PathVariable Integer id,
      @RequestBody ClienteJuridico clienteJuridico) {
    clienteJuridico.setId(id);
    return ResponseEntity.ok(service.save(clienteJuridico));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMINISTRADOR')")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
