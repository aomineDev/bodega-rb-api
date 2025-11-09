package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "categoria_id")
  private Integer id;

  @Column(name = "nombre", nullable = false, unique = true, length = 70)
  private String nombre;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;
}
