package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ingreso_productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngresoProducto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ingreso_producto_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "asistente_almacen_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado asistenteAlmacen;

  @ManyToOne
  @JoinColumn(name = "proveedor_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Proveedor proveedor;

  @Column(name = "fecha_ingreso", nullable = false)
  private LocalDate fechaIngreso;

  @Column(name = "hora_ingreso", nullable = false)
  private LocalTime horaIngreso;

  @Column(name = "estado", length = 50)
  private String estado;

  @ManyToOne
  @JoinColumn(name = "jefe_almacen_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado jefeAlmacen;

  @Column(name = "fecha_aprobacion")
  private LocalDate fechaAprobacion;

  @Column(name = "observaciones", length = 200)
  private String observaciones;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "ingreso_producto_id")
  private List<DetalleIngreso> detalleIngresos;
}
