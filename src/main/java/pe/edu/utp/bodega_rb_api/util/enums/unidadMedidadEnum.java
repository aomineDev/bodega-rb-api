package pe.edu.utp.bodega_rb_api.util.enums;

public enum UnidadMedidadEnum {

  KILOGRAMO("KILOGRAMO"),
  LITRO("LITRO"),
  UNIDAD("UNIDAD");

  private final String name;

  UnidadMedidadEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}