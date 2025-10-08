package pe.edu.utp.bodega_rb_api.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura extends Comprobante {
  @ManyToOne
  @JoinColumn(name = "cliente_juridico_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private ClienteJuridico clienteJuridico;
}
