package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "ingresos_productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngresoProducto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ingreso_producto_id")
  private Integer ingresoProductoId;

  @ManyToOne
  @JoinColumn(name = "asistente_almacen_id")
  private Empleado asistenteAlmacen;

  @ManyToOne
  @JoinColumn(name = "proveedor_id")
  private Proveedor proveedor;

  @Column(name = "fecha_ingreso", nullable = false)
  private LocalDate fechaIngreso;

  @Column(name = "hora_ingreso", nullable = false)
  private LocalTime horaIngreso;

  @Column(nullable = false, length = 30)
  private String estado;

  @ManyToOne
  @JoinColumn(name = "jefe_almacen_id")
  private Empleado jefeAlmacen;

  @Column(name = "fecha_aprobacion")
  private LocalDate fechaAprobacion;

  @Column(length = 200)
  private String observaciones;

  @OneToMany(mappedBy = "ingresoProducto", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleIngreso> detalleIngresos;
}
