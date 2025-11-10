package pe.edu.utp.bodega_rb_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

  @Column(name = "direccion", length = 100)
  protected String direccion;

  @Column(name = "telefono", unique = true, columnDefinition = "CHAR(9)")
  protected String telefono;

  @Column(name = "email", unique = true, length = 150)
  protected String email;

  // el '' del fronted, lo convierte a nulo
  @PrePersist
  @PreUpdate
  protected void normalizeFields() {
    if (email != null && email.isBlank()) {
      email = null;
    }
  }
}