package pe.edu.utp.bodega_rb_api.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "empleado")
public class Empleado extends PersonaNatural {

  @Column(name = "clave", nullable = false, length = 30)
  private String clave;

  @Column(name = "imagen", nullable = false, length = 100)
  private String imagen;

  @ManyToOne
  @JoinColumn(name = "rol_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Rol rolId;
}
