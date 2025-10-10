package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetalleAuditoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer detalleAuditoriaId;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  private Integer stockSistema;
  private Integer stockFisico;
  private Integer diferencia;
  private String observaciones;

  @ManyToOne
  @JoinColumn(name = "auditoria_inventario_id", nullable = false)
  private AuditoriaInventario auditoriaInventario;
}
