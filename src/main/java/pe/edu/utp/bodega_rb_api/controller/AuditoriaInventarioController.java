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

import pe.edu.utp.bodega_rb_api.model.AuditoriaInventario;
import pe.edu.utp.bodega_rb_api.service.AuditoriaInventarioService;

@RestController
@RequestMapping("/api/AuditoriaInventario")
public class AuditoriaInventarioController {
    @Autowired
    AuditoriaInventarioService service;

    @GetMapping
    public ResponseEntity<List<AuditoriaInventario>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaInventario> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuditoriaInventario> save(@RequestBody AuditoriaInventario AuditoriaInventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(AuditoriaInventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaInventario> update(@PathVariable Integer id,
            @RequestBody AuditoriaInventario AuditoriaInventario) {
        return ResponseEntity.ok(service.save(AuditoriaInventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}