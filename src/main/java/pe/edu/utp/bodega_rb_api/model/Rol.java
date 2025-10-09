package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rol_id")
  private Integer rolId;

  @Column(name = "nombre", nullable = false, length = 50, unique = true)
  private String nombre;
}