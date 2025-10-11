package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "detalles_auditoria")
@Setter
public class DetalleAuditoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detalle_auditoria_id", nullable = false)
  private Integer detalleAuditoriaId;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  @Column(name = "stock_sistema", nullable = false)
  private Integer stockSistema;

  @Column(name = "stock_fisico", nullable = false)
  private Integer stockFisico;

  @Column(name = "diferencia", nullable = false)
  private Integer diferencia;

  @Column(name = "observaciones", nullable = true)
  private String observaciones;

}
