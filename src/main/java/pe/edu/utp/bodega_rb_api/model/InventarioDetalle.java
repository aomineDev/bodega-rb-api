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
@Table(name = "inventario_detalles")
public class InventarioDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventario_detalle_id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  @Column(name = "stock_sistema", nullable = false)
  private Double stockSistema;

  @Column(name = "stock_fisico", nullable = false)
  private Double stockFisico = 0d;

  @Column(name = "diferencia")
  private Double diferencia;

  @Column(name = "observaciones")
  private String observaciones;

  public void addStock(Double stock) {
    this.stockFisico += stock;
    this.diferencia = this.calcDiferencia();
  }

  public void setStockFisico(Double stockFisico) {
    this.stockFisico = stockFisico;
    this.diferencia = this.calcDiferencia();
  }

  public Double calcDiferencia() {
    return this.stockSistema - this.stockFisico;
  }
}
