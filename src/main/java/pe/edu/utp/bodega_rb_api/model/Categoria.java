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
  private Integer categoriaId;

  @Column(nullable = false, unique = true, length = 50)
  private String nombre;

  @Column(length = 200)
  private String descripcion;
}
