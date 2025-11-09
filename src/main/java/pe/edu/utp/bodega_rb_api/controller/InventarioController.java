package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Inventario;
import pe.edu.utp.bodega_rb_api.service.InventarioService;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {
	@Autowired
	InventarioService inventarioService;

	@GetMapping
	public ResponseEntity<List<Inventario>> findAll(@RequestParam(value = "estado", required = false) Boolean estado) {
		if (estado != null)
			return ResponseEntity.ok(inventarioService.findByEstado(estado));

		return ResponseEntity.ok(inventarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Inventario> findById(@PathVariable Integer id) {
		return inventarioService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Inventario> save(@RequestBody Inventario inventario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Inventario> update(@PathVariable Integer id, @RequestBody Inventario inventario) {
		return inventarioService.updateById(id, inventario)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PatchMapping("/asistente/{id}")
	public ResponseEntity<Inventario> addAsistenteAlmacen(@PathVariable Integer id,
			@RequestBody Empleado empleado) {
		return inventarioService.addAsistenteAlmacen(id, empleado)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PatchMapping("/cerrar/{id}")
	public ResponseEntity<Inventario> cerrarInventario(@PathVariable Integer id) {
		return inventarioService.cerrarInventario(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		inventarioService.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}