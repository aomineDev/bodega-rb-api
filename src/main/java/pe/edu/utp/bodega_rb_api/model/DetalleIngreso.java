package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "detalles_ingresos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleIngreso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detalle_ingreso_id")
  private Integer detalleIngresoId;

  @Column(name = "lote", nullable = false, length = 20)
  private String lote;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

  @Column(name = "precio_compra", nullable = false)
  private Double precioCompra;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Producto producto;

  @Column(name = "fecha_produccion", nullable = false)
  private LocalDate fechaProduccion;

  @Column(name = "fecha_vencimiento", nullable = false)
  private LocalDate fechaVencimiento;
}
