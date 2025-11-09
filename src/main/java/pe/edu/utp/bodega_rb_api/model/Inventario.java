package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Table(name = "inventarios")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventario_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Categoria categoria;

  @Column(name = "fecha_inventario")
  private LocalDate fechaInventario;

  @Column(name = "estado")
  private Boolean estado = true;

  @ManyToMany
  @JoinTable(name = "inventario_asistente_almacen", joinColumns = @JoinColumn(name = "inventario_id"), inverseJoinColumns = @JoinColumn(name = "asistente_almacen_id"))
  private List<Empleado> asistenteAlmacenList = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "inventario_id")
  private List<InventarioDetalle> inventarioDetalles = new ArrayList<>();

  public void addInventarioDetalle(InventarioDetalle inventarioDetalle) {
    inventarioDetalles.add(inventarioDetalle);
  }

  public void clearInventarioDetalles() {
    inventarioDetalles.clear();
  }

  public void addAsistenteAlmacen(Empleado asistenteAlmacen) {
    this.asistenteAlmacenList.add(asistenteAlmacen);
  }
}