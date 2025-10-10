package pe.edu.utp.bodega_rb_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.bodega_rb_api.model.DetalleAuditoria;
import pe.edu.utp.bodega_rb_api.service.DetalleAuditoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-auditoria")
public class DetalleAuditoriaController {

    @Autowired
    private DetalleAuditoriaService detalleAuditoriaService;

    @PostMapping
    public DetalleAuditoria create(@RequestBody DetalleAuditoria detalleAuditoria) {
        return detalleAuditoriaService.save(detalleAuditoria);
    }

    @GetMapping
    public List<DetalleAuditoria> getAll() {
        return detalleAuditoriaService.findAll();
    }

    @GetMapping("/{id}")
    public DetalleAuditoria getById(@PathVariable Integer id) {
        return detalleAuditoriaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        detalleAuditoriaService.deleteById(id);
    }
}