package pe.edu.utp.bodega_rb_api.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detalle_venta_id")
  private Integer detalleVentaId;

  @Column(name = "cantidad", nullable = false)
  private Double cantidad;

  @Column(name = "precio_unitario", nullable = false)
  private Double precioUnitario;

  @Column(name = "sub_total", nullable = false)
  private Double subTotal;

  @ManyToOne
  @JoinColumn(name = "producto_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Producto producto;
}
