package pe.edu.utp.bodega_rb_api.model;

import java.time.LocalDate;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proximos_a_vencer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProximoAVencer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "proximos_a_vencer_id", nullable = false)
  private Integer id;

  @Column(name = "cantidad", nullable = false)
  private double cantidad;

  @Column(name = "fecha_vencimiento", nullable = false)
  private LocalDate fechaVencimiento;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Producto producto;
}
