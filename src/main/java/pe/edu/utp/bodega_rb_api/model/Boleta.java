package pe.edu.utp.bodega_rb_api.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "boletas")
public class Boleta extends Comprobante {
  @ManyToOne
  @JoinColumn(name = "cliente_natural_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private ClienteNatural clienteNatural;
}
