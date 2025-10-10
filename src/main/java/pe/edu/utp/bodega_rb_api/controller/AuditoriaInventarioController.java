package pe.edu.utp.bodega_rb_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.bodega_rb_api.model.AuditoriaInventario;
import pe.edu.utp.bodega_rb_api.service.AuditoriaInventarioService;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria-inventario")
public class AuditoriaInventarioController {

    @Autowired
    private AuditoriaInventarioService auditoriaInventarioService;

    @PostMapping
    public AuditoriaInventario create(@RequestBody AuditoriaInventario auditoriaInventario) {
        return auditoriaInventarioService.save(auditoriaInventario);
    }

    @GetMapping
    public List<AuditoriaInventario> getAll() {
        return auditoriaInventarioService.findAll();
    }

    @GetMapping("/{id}")
    public AuditoriaInventario getById(@PathVariable Integer id) {
        return auditoriaInventarioService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        auditoriaInventarioService.deleteById(id);
    }
}