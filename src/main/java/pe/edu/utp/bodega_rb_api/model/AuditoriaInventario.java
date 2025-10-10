package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
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
  private Empleado asistenteAlmacen;

  @OneToMany(mappedBy = "auditoriaInventario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleAuditoria> detalleAuditorias;
}