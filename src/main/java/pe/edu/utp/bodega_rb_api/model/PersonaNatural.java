package pe.edu.utp.bodega_rb_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "personas_naturales")
public class PersonaNatural extends Persona {
  @Column(name = "dni", unique = true, nullable = false, columnDefinition = "CHAR(8)")
  private char dni;

  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @Column(name = "apellido_paterno", nullable = false, length = 50)
  private String apellidoPaterno;

  @Column(name = "apellido_materno", nullable = false, length = 50)
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento", nullable = false)
  private LocalDate fechaNacimiento;
}
