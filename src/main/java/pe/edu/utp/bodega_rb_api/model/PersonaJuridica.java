package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "personas_juridicas")
public class PersonaJuridica extends Persona {
  @Column(name = "ruc", unique = true, nullable = false, columnDefinition = "CHAR(11)")
  private String ruc;

  @Column(name = "razon_social", nullable = false, length = 300)
  private String razonSocial;

  @Column(name = "tipo_contribuyente", nullable = false, length = 100)
  private String tipoContribuyente;

  @Column(name = "actividad_economica", nullable = false, length = 300)
  private String actividadEconomica;
}
