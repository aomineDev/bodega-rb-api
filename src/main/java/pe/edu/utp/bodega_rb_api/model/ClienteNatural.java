package pe.edu.utp.bodega_rb_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes_naturales")
public class ClienteNatural extends PersonaNatural {
  @Column(name = "fecha_registro", nullable = false)
  private LocalDate fechaRegistro;
}
