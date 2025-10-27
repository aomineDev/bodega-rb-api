package pe.edu.utp.bodega_rb_api.integration.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReniecCustomer {
  private String first_name;
  private String first_last_name;
  private String second_last_name;
  private String full_name;
  private String document_number;
}
