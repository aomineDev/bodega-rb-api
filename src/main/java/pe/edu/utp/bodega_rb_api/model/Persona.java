package pe.edu.utp.bodega_rb_api.model;

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
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "persona_id")
  protected Integer id;

  @Column(name = "direccion", length = 70, nullable = true)
  protected String direccion;

  @Column(name = "telefono", unique = true, columnDefinition = "CHAR(9)", nullable = true)
  protected String telefono;

  @Column(name = "email", unique = true, length = 100, nullable = true)
  protected String email;
}