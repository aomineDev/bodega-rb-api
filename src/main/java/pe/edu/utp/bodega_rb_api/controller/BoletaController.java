package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import pe.edu.utp.bodega_rb_api.model.Boleta;
import pe.edu.utp.bodega_rb_api.service.BoletaService;
import pe.edu.utp.bodega_rb_api.service.ComprobantePdfService;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {
  @Autowired
  BoletaService service;

  @Autowired
  ComprobantePdfService comprobantePdfService;

  @GetMapping
  public ResponseEntity<List<Boleta>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Boleta> findById(@PathVariable Integer id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CAJERO')")
  public ResponseEntity<Boleta> save(@RequestBody Boleta boleta) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(boleta));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMINISTRADOR')")
  public ResponseEntity<Boleta> update(@PathVariable Integer id, @RequestBody Boleta boleta) {
    return ResponseEntity.ok(service.save(boleta));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMINISTRADOR')")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/pdf")
  public ResponseEntity<byte[]> generarPdf(@PathVariable Integer id) throws Exception {
    Boleta boleta = service.findById(id)
        .orElseThrow(() -> new RuntimeException("Boleta no encontrada con ID: " + id));

    byte[] pdf = comprobantePdfService.generarPdf(boleta);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=boleta_" + id + ".pdf")
        .contentType(MediaType.APPLICATION_PDF)
        .body(pdf);
  }

}