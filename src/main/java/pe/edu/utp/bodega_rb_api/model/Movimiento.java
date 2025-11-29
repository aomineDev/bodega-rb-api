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
@Table(name = "movimientos")
public class Movimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "movimiento_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "caja_id", nullable = false)
  private Caja caja;

  @ManyToOne
  @JoinColumn(name = "comprobante_id")
  private Comprobante comprobante;

  @Column(name = "tipo", nullable = false)
  private String tipo;
  // "VENTA", "VUELTO", "INGRESO", "RETIRO"

  @Column(name = "monto", nullable = false)
  private Double monto;

  @Column(name = "detalle")
  private String detalle;

  @Column(name = "fecha", nullable = false)
  private LocalDate fecha;

  @Column(name = "hora", nullable = false)
  private LocalTime hora;

  @ManyToOne
  @JoinColumn(name = "empleado_id")
  private Empleado empleado;
}
