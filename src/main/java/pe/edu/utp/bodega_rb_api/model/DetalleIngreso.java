package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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

  @Column(nullable = false, length = 20)
  private String lote;

  @Column(nullable = false)
  private Integer cantidad;

  @Column(name = "precio_compra", nullable = false)
  private Double precioCompra;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  @Column(name = "fecha_produccion")
  private LocalDate fechaProduccion;

  @Column(name = "fecha_vencimiento")
  private LocalDate fechaVencimiento;

  @ManyToOne
  @JoinColumn(name = "ingreso_producto_id", nullable = false)
  private IngresoProducto ingresoProducto;
}
