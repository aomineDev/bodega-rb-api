package pe.edu.utp.bodega_rb_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.service.IntegrationService;

@RestController
@RequestMapping("/api/integracion")
public class IntegrationController {

  @Autowired
  IntegrationService integrationService;

  @GetMapping("/buscar/reniec/{dni}")
  public ResponseEntity<?> getCustomerByDni(@PathVariable String dni) {
    try {
      var data = integrationService.getCustomerByDni(dni);
      return ResponseEntity.ok(data);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error consultando RENIEC");
    }
  }

  @GetMapping("/buscar/sunat/{ruc}")
  public ResponseEntity<?> getCustomerByRuc(@PathVariable String ruc) {
    try {
      var data = integrationService.getCustomerByRuc(ruc);
      return ResponseEntity.ok(data);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error consultando SUNAT");
    }
  }

  @GetMapping("crear/reniec/{dni}")
  public ResponseEntity<?> createCustomerByDni(@PathVariable String dni) {
    try {
      var data = integrationService.createCustomerByDni(dni);
      return ResponseEntity.ok(data);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error consultando RENIEC");
    }
  }

  @GetMapping("crear/sunat/{ruc}")
  public ResponseEntity<?> createByRuc(@PathVariable String ruc) {
    try {
      var data = integrationService.createCustomerByRuc(ruc);
      return ResponseEntity.ok(data);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error consultando SUNAT");
    }
  }

}
