package pe.edu.utp.bodega_rb_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.bodega_rb_api.dto.DashboardMetrics;
import pe.edu.utp.bodega_rb_api.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
  @Autowired
  private DashboardService dashboardService;

  @GetMapping
  public ResponseEntity<DashboardMetrics> getDashboardMetrics() {
    return ResponseEntity.ok(dashboardService.getDashboardMetrics());
  }
}
