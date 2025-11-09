package pe.edu.utp.bodega_rb_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDetalleDTO {
  private Double stock;
  private String observaciones;
}
