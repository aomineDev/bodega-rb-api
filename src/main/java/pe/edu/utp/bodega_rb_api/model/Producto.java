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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "producto_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  @OnDelete(action = OnDeleteAction.RESTRICT)
  private Categoria categoria;

  @ManyToOne
  @JoinColumn(name = "proveedor_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Proveedor proveedor;

  @Column(name = "codigo_barra", unique = true)
  private String codigoBarra;

  @Column(name = "nombre", unique = true, nullable = false, length = 50)
  private String nombre;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "precio_unitario", nullable = false)
  private Double precioUnitario;

  @Column(name = "precio_promocion")
  private Double precioPromocion;

  @Column(name = "inicio_promocion")
  private LocalDate inicioPromocion;

  @Column(name = "fin_promocion")
  private LocalDate finPromocion;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "unidad_medida", nullable = false, length = 10)
  private String unidadMedida;

  @Column(name = "imagen", length = 100)
  private String imagen;

}
