package pe.edu.utp.bodega_rb_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.dto.OfferPrice;
import pe.edu.utp.bodega_rb_api.model.ProximoAVencer;
import pe.edu.utp.bodega_rb_api.service.ProximoAVencerService;

@RestController
@RequestMapping("/proximosAVencer")
public class ProximoAVencerController {

  @Autowired
  private ProximoAVencerService proximosAVencerService;

  @GetMapping
  public ResponseEntity<List<ProximoAVencer>> findAll() {
    return ResponseEntity.ok(proximosAVencerService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProximoAVencer> findById(@PathVariable Integer id) {
    return proximosAVencerService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ProximoAVencer> save(@RequestBody ProximoAVencer entity) {
    return ResponseEntity.ok(proximosAVencerService.save(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProximoAVencer> update(@PathVariable Integer id, @RequestBody ProximoAVencer entity) {
    return ResponseEntity.ok(proximosAVencerService.save(entity));
  }

  @PatchMapping("/{id}/2x1")
  public ResponseEntity<Void> handle2x1(@PathVariable Integer id) {
    proximosAVencerService.handle2x1(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/offer")
  public ResponseEntity<Void> handleOffer(@PathVariable Integer id, @RequestBody OfferPrice offerPrice) {
    proximosAVencerService.handleOfferPrice(id, offerPrice.getPrecio());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    proximosAVencerService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
