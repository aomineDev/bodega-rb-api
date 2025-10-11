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
@Setter
public class AuditoriaInventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer auditoriaInventarioId;

  private LocalDate fechaAuditoria;
  private LocalTime horaAuditoria;

  @ManyToOne
  @JoinColumn(name = "asistente_almacen_id", nullable = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado asistenteAlmacen;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "auditoria_inventario_id")
  private List<DetalleAuditoria> detalleAuditorias;
}