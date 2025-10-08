package pe.edu.utp.bodega_rb_api.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "comprobantes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comprobante {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comprobante_id")
  protected Integer comprobanteId;

  @Column(name = "fecha", nullable = false)
  protected LocalDate fecha;

  @Column(name = "hora", nullable = false)
  protected LocalTime hora;

  @Column(name = "grabado", nullable = false)
  protected Double grabado;

  @Column(name = "igv", nullable = false)
  protected Double igv;

  @Column(name = "precio_total", nullable = false)
  protected Double precioTotal;

  @Column(name = "estado", nullable = false)
  protected Boolean estado;

  @Column(name = "vuelto")
  protected Double vuelto;

  // Falta empleado

  // Falta arreglo de detalle venta
  // @OneToMany
  // protected List<DetalleVneta> detalleVentas;
}