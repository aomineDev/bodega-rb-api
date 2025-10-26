package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "detalles_auditoria")
public class DetalleAuditoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detalle_auditoria_id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  @Column(name = "stock_sistema", nullable = false)
  private Integer stockSistema;

  @Column(name = "stock_fisico", nullable = false)
  private Integer stockFisico;

  @Column(name = "diferencia")
  private Integer diferencia;

  @Column(name = "observaciones")
  private String observaciones;

}
