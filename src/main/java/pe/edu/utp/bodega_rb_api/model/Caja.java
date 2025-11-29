package pe.edu.utp.bodega_rb_api.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "caja")
public class Caja {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "caja_id")
  private Integer id;

  @Column(name = "fecha_apertura", nullable = false)
  private LocalDate fechaApertura;

  @Column(name = "hora_apertura", nullable = false)
  private LocalTime horaApertura;

  @Column(name = "monto_apertura", nullable = false)
  private Double montoApertura;

  @Column(name = "fecha_cierre")
  private LocalDate fechaCierre;

  @Column(name = "hora_cierre")
  private LocalTime horaCierre;

  @Column(name = "monto_cierre")
  private Double montoCierre;

  @Column(name = "estado", nullable = false)
  private Boolean estado;

  @ManyToOne
  @JoinColumn(name = "empleado_apertura_id")
  private Empleado empleadoApertura;

  @ManyToOne
  @JoinColumn(name = "empleado_cierre_id")
  private Empleado empleadoCierre;
}
