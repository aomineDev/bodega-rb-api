package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "auditorias_inventario")
@Getter
@Table(name = "auditorias_inventario")
@Setter
public class AuditoriaInventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "auditoria_inventario_id", nullable = false)
  private Integer auditoriaInventarioId;

  @Column(name = "fecha_auditoria", nullable = false)
  private LocalDate fechaAuditoria;

  @Column(name = "hora_auditoria", nullable = false)
  private LocalTime horaAuditoria;

  @ManyToOne
  @JoinColumn(name = "asistente_almacen_id", nullable = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado asistenteAlmacen;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "auditoria_inventario_id")
  private List<DetalleAuditoria> detalleAuditorias;
}