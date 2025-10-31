package pe.edu.utp.bodega_rb_api.util;

public class ConversionToLetter {

  private static final String[] UNIDADES = {
      "", "UNO", "DOS", "TRES", "CUATRO", "CINCO",
      "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ",
      "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE",
      "DIECISÉIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE"
  };

  private static final String[] DECENAS = {
      "VEINTI", "TREINTA", "CUARENTA", "CINCUENTA",
      "SESENTA", "SETENTA", "OCHENTA", "NOVENTA"
  };

  private static final String[] CENTENAS = {
      "CIENTO", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS",
      "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS"
  };

  public static String convertir(double numero) {
    long parteEntera = (long) numero;
    int centimos = (int) Math.round((numero - parteEntera) * 100);
    String letras = convertirEntero(parteEntera);
    return letras + " CON " + String.format("%02d", centimos) + "/100";
  }

  private static String convertirEntero(long numero) {
    if (numero == 0)
      return "CERO";
    if (numero < 0)
      return "MENOS " + convertirEntero(-numero);

    if (numero <= 20)
      return UNIDADES[(int) numero];
    if (numero < 30)
      return "VEINTI" + UNIDADES[(int) (numero - 20)].toLowerCase();

    if (numero < 100) {
      int decena = (int) (numero / 10);
      int unidad = (int) (numero % 10);
      return DECENAS[decena - 2] + (unidad > 0 ? " Y " + UNIDADES[unidad] : "");
    }

    if (numero == 100)
      return "CIEN";
    if (numero < 1000) {
      int centena = (int) (numero / 100);
      return CENTENAS[centena - 1] + (numero % 100 > 0 ? " " + convertirEntero(numero % 100) : "");
    }

    if (numero < 2000)
      return "MIL " + convertirEntero(numero % 1000);
    if (numero < 1_000_000)
      return convertirEntero(numero / 1000) + " MIL" + (numero % 1000 > 0 ? " " + convertirEntero(numero % 1000) : "");
    if (numero < 2_000_000)
      return "UN MILLÓN " + convertirEntero(numero % 1_000_000);
    if (numero < 1_000_000_000)
      return convertirEntero(numero / 1_000_000) + " MILLONES"
          + (numero % 1_000_000 > 0 ? " " + convertirEntero(numero % 1_000_000) : "");

    return "NÚMERO DEMASIADO GRANDE";
  }
}
