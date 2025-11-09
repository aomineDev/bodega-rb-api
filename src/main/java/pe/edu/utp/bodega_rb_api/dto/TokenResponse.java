package pe.edu.utp.bodega_rb_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.utp.bodega_rb_api.model.Empleado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
  private String token;
  private final String type = "Bearer";
  private Empleado user;

}
