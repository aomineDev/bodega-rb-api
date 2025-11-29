package pe.edu.utp.bodega_rb_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenCajaDTO {
  private Double apertura;
  private Double ventasEfectivo;
  private Double ventasTarjeta;
  private Double ventasYape;
  private Double ventasPlin;
  private Double ventasDelDia;
  private Double ventasElectronicas;
  private Double ingresos;
  private Double egresosVuelto;
  private Double egresosRetiro;

  private Double ingresosTotales;
  private Double egresosTotales;
  private Double saldoCalculado;
}
