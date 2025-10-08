package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas_juridicas")
public class PersonaJuridica extends Persona {
  @Column(name = "ruc", nullable = false, columnDefinition = "CHAR(11)")
  private String ruc;

  @Column(name = "razon_social", nullable = false, length = 100)
  private String razonSocial;

  @Column(name = "nombre_comercial", nullable = false, length = 100)
  private String nombreComercial;

  @Column(name = "tipo_contribuyente", nullable = false, length = 50)
  private String tipoContribuyente;

  @Column(name = "actividad_economica", nullable = false, length = 100)
  private String actividadEconomica;
}
