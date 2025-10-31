package pe.edu.utp.bodega_rb_api.integration.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SunatCustomer {
  private String razon_social;
  private String numero_documento;
  private String estado;
  private String condicion;
  private String direccion;
  private String ubigeo;
  private String actividad_economica;
  private String tipo;
  private String comercial;
  private String via_tipo;
  private String via_nombre;
  private String zona_codigo;
  private String zona_tipo;
  private String numero;
  private String interior;
  private String lote;
  private String dpto;
  private String manzana;
  private String kilometro;
  private String distrito;
  private String provincia;
  private String departamento;
  private Boolean es_agente_retencion;
  private Boolean es_buen_contribuyente;
}