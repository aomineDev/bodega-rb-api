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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "arqueos")
public class Arqueo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "caja_id", nullable = false)
  private Caja caja;

  @ManyToOne
  @JoinColumn(name = "cajero_id", nullable = false)
  private Empleado cajero;

  @Column(name = "fecha", nullable = false)
  private LocalDate fecha;

  @Column(name = "hora", nullable = false)
  private LocalTime hora;

  @Column(name = "total_fisico", nullable = false)
  private Double totalFisico;

  @Column(name = "total_sistema", nullable = false)
  private Double totalSistema;

  @Column(name = "diferencia", nullable = false)
  private Double diferencia;

  @Column(name = "observaciones")
  private String observaciones;
}
