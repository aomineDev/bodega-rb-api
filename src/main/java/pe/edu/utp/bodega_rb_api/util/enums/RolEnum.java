package pe.edu.utp.bodega_rb_api.util.enums;

public enum RolEnum {
  CAJERO("ROLE_CAJERO"),
  ADMINISTRADOR("ROLE_ADMINISTRADOR"),
  ASISTENTE("ROLE_ASISTENTE"),
  JEFE_ALMACEN("ROLE_JEFE_ALMACEN");
  ;

  private final String name;

  RolEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
