package pe.edu.utp.bodega_rb_api.util.enums;

public enum unidadMedidadEnum {

  KILOGRAMO("KILOGRAMO"),
  LITRO("LITRO"),
  UNIDAD("UNIDAD");

  private final String name;

  unidadMedidadEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}