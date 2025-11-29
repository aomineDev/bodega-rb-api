package pe.edu.utp.bodega_rb_api.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pe.edu.utp.bodega_rb_api.dto.ResumenCajaDTO;
import pe.edu.utp.bodega_rb_api.model.Boleta;
import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.model.ClienteJuridico;
import pe.edu.utp.bodega_rb_api.model.ClienteNatural;
import pe.edu.utp.bodega_rb_api.model.Comprobante;
import pe.edu.utp.bodega_rb_api.model.DetalleVenta;
import pe.edu.utp.bodega_rb_api.model.Factura;
import pe.edu.utp.bodega_rb_api.util.ConversionToLetter;

@Service
public class ComprobantePdfService {

  public byte[] generarPdf(Comprobante comprobante) throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Document document = new Document(new Rectangle(160, 800), 10, 10, 10, 10);
    PdfWriter.getInstance(document, baos);
    document.open();

    document.add(p(("Embutidos RB"), Element.ALIGN_CENTER));
    document.add(p(("Huaycán, Av. Andrés Avelino Cáceres"), Element.ALIGN_CENTER));
    document.add(p(("ATE - LIMA - LIMA"), Element.ALIGN_CENTER));
    document.add(p(("Teléfono: 935456467"), Element.ALIGN_CENTER));
    document.add(p("-".repeat(23)));

    if (comprobante instanceof Boleta boleta) {
      ClienteNatural c = boleta.getClienteNatural();
      document.add(p(("BOLETA DE VENTA ELECTRONICA"), Element.ALIGN_CENTER));
      document.add(p("B-" + boleta.getId(), Element.ALIGN_CENTER));
      document.add(p("-".repeat(23)));
      document.add(p("Cliente: " + c.getNombre() + " " + c.getApellidoPaterno() + " " + c.getApellidoMaterno()));
      document.add(p("DNI: " + c.getDni()));
    } else if (comprobante instanceof Factura factura) {
      ClienteJuridico c = factura.getClienteJuridico();
      document.add(p(("FACTURA DE VENTA ELECTRONICA"), Element.ALIGN_CENTER));
      document.add(p("F-" + factura.getId(), Element.ALIGN_CENTER));
      document.add(p("-".repeat(23)));
      document.add(p("RUC: " + c.getRuc()));
      document.add(p("Razón Social: " + c.getRazonSocial()));
      document.add(p("Dirección: " + c.getDireccion()));
    }

    document.add(p("-".repeat(23)));
    document.add(p("Fecha: " + comprobante.getFecha()));
    document.add(p("Hora: " + comprobante.getHora()));

    document.add(p("-".repeat(23)));

    PdfPTable table1 = new PdfPTable(3);
    table1.setWidthPercentage(100);
    table1.setWidths(new float[] { 2, 1, 2 });

    table1.addCell(cell("Producto", Element.ALIGN_LEFT));
    table1.addCell(cell("Cant.", Element.ALIGN_CENTER));
    table1.addCell(cell("Subtotal", Element.ALIGN_RIGHT));

    for (DetalleVenta det : comprobante.getDetalleVentas()) {
      table1.addCell(cell(det.getProducto().getNombre(), Element.ALIGN_LEFT));
      table1.addCell(cell(String.format("%.2f", det.getCantidad()), Element.ALIGN_CENTER));
      table1.addCell(cell(String.format("S/ %.2f", det.getSubTotal()), Element.ALIGN_RIGHT));
    }

    document.add(table1);

    document.add(p("-".repeat(23)));

    PdfPTable table2 = new PdfPTable(2);
    table2.setWidthPercentage(100);
    table2.setWidths(new float[] { 2, 1 });

    table2.addCell(cell("GRAVADO:", Element.ALIGN_LEFT));
    table2.addCell(cell(String.format("S/ %.2f", comprobante.getGrabado()), Element.ALIGN_RIGHT));

    table2.addCell(cell("I.G.V:", Element.ALIGN_LEFT));
    table2.addCell(cell(String.format("S/ %.2f", comprobante.getIgv()), Element.ALIGN_RIGHT));

    table2.addCell(cell("TOTAL:", Element.ALIGN_LEFT));
    table2.addCell(cell(String.format("S/ %.2f", comprobante.getPrecioTotal()), Element.ALIGN_RIGHT));

    document.add(table2);

    document.add(p("-".repeat(23)));
    String totalEnLetras = ConversionToLetter.convertir(comprobante.getPrecioTotal());
    document.add(p("SON: " + totalEnLetras + " SOLES"));
    document.add(p("-".repeat(23)));

    document.add(p("GRACIAS POR SU COMPRA", Element.ALIGN_CENTER));

    document.add(p("-".repeat(23)));

    document.close();
    return baos.toByteArray();
  }

  public byte[] generarPdfCierreCaja(Caja caja, ResumenCajaDTO resumen) throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Document document = new Document(new Rectangle(160, 800), 10, 10, 10, 10);
    PdfWriter.getInstance(document, baos);
    document.open();

    document.add(p("CIERRE DE CAJA", Element.ALIGN_CENTER));
    document.add(p("Caja ID: " + caja.getId(), Element.ALIGN_CENTER));
    document.add(p("Apertura: " + caja.getFechaApertura() + " " + caja.getHoraApertura(), Element.ALIGN_CENTER));
    document.add(p("Cierre: " + caja.getFechaCierre() + " " + caja.getHoraCierre(), Element.ALIGN_CENTER));
    document
        .add(p("Cajero: " + caja.getEmpleadoCierre().getNombre() + " " + caja.getEmpleadoCierre().getApellidoPaterno(),
            Element.ALIGN_CENTER));

    // DETALLE DE VENTA
    document.add(p("-".repeat(23)));
    document.add(p("DETALLE DE VENTA", Element.ALIGN_CENTER));

    PdfPTable tableVenta = new PdfPTable(2);
    tableVenta.setWidthPercentage(100);
    tableVenta.setWidths(new float[] { 2, 1 });

    tableVenta.addCell(cell("Efectivo", Element.ALIGN_LEFT));
    tableVenta.addCell(cell("S/ " + resumen.getVentasEfectivo(), Element.ALIGN_RIGHT));
    tableVenta.addCell(cell("Tarjeta", Element.ALIGN_LEFT));
    tableVenta.addCell(cell("S/ " + resumen.getVentasTarjeta(), Element.ALIGN_RIGHT));
    tableVenta.addCell(cell("Yape", Element.ALIGN_LEFT));
    tableVenta.addCell(cell("S/ " + resumen.getVentasYape(), Element.ALIGN_RIGHT));
    tableVenta.addCell(cell("Plin", Element.ALIGN_LEFT));
    tableVenta.addCell(cell("S/ " + resumen.getVentasPlin(), Element.ALIGN_RIGHT));

    document.add(tableVenta);

    // DETALLE GENERAL
    document.add(p("-".repeat(23)));
    document.add(p("DETALLE GENERAL", Element.ALIGN_CENTER));

    PdfPTable tableGeneral = new PdfPTable(2);
    tableGeneral.setWidthPercentage(100);
    tableGeneral.setWidths(new float[] { 2, 1 });

    tableGeneral.addCell(cell("Apertura", Element.ALIGN_LEFT));
    tableGeneral.addCell(cell("S/ " + resumen.getApertura(), Element.ALIGN_RIGHT));
    tableGeneral.addCell(cell("Ventas Efectivo", Element.ALIGN_LEFT));
    tableGeneral.addCell(cell("S/ " + resumen.getVentasEfectivo(), Element.ALIGN_RIGHT));
    tableGeneral.addCell(cell("Ingresos", Element.ALIGN_LEFT));
    tableGeneral.addCell(cell("S/ " + resumen.getIngresos(), Element.ALIGN_RIGHT));
    tableGeneral.addCell(cell("Egresos Vuelto", Element.ALIGN_LEFT));
    tableGeneral.addCell(cell("S/ " + resumen.getEgresosVuelto(), Element.ALIGN_RIGHT));
    tableGeneral.addCell(cell("Egresos Retiro", Element.ALIGN_LEFT));
    tableGeneral.addCell(cell("S/ " + resumen.getEgresosRetiro(), Element.ALIGN_RIGHT));

    document.add(tableGeneral);

    // DETALLE DE CUADRE FINAL
    document.add(p("-".repeat(23)));
    document.add(p("DETALLE DE CUADRE FINAL", Element.ALIGN_CENTER));

    PdfPTable tableCuadre = new PdfPTable(2);
    tableCuadre.setWidthPercentage(100);
    tableCuadre.setWidths(new float[] { 2, 1 });

    tableCuadre.addCell(cell("Ingresos Totales", Element.ALIGN_LEFT));
    tableCuadre.addCell(cell("S/ " + resumen.getIngresosTotales(), Element.ALIGN_RIGHT));
    tableCuadre.addCell(cell("Egresos Totales", Element.ALIGN_LEFT));
    tableCuadre.addCell(cell("S/ " + resumen.getEgresosTotales(), Element.ALIGN_RIGHT));
    tableCuadre.addCell(cell("Saldo Calculado", Element.ALIGN_LEFT));
    tableCuadre.addCell(cell("S/ " + resumen.getSaldoCalculado(), Element.ALIGN_RIGHT));

    document.add(tableCuadre);

    // DETALLE DE CUADRE FINAL
    document.add(p("-".repeat(23)));

    PdfPTable tableFinal = new PdfPTable(2);
    tableFinal.setWidthPercentage(100);
    tableFinal.setWidths(new float[] { 2, 1 });

    tableFinal.addCell(cell("TOTAL EFECTIVO EN CAJA", Element.ALIGN_LEFT));
    tableFinal.addCell(cell("S/ " + resumen.getSaldoCalculado(), Element.ALIGN_RIGHT));
    tableFinal.addCell(cell("TOTAL CTA BANCARIA", Element.ALIGN_LEFT));
    tableFinal.addCell(cell("S/ " + resumen.getVentasElectronicas(), Element.ALIGN_RIGHT));
    tableFinal.addCell(cell("TOTAL CUADRE", Element.ALIGN_LEFT));
    tableFinal
        .addCell(cell("S/ " + (resumen.getSaldoCalculado() + resumen.getVentasElectronicas()), Element.ALIGN_RIGHT));

    document.add(tableFinal);

    document.add(p("-".repeat(23)));
    document.add(p("GRACIAS POR SU COMPRA", Element.ALIGN_CENTER));

    document.close();
    return baos.toByteArray();
  }

  private Paragraph p(String txt) {
    Font font = new Font(Font.FontFamily.COURIER, 10);

    return new Paragraph(txt, font);
  }

  private Paragraph p(String txt, int alignmet) {
    Font font = new Font(Font.FontFamily.COURIER, 10);
    Paragraph paragraph = new Paragraph(txt, font);
    paragraph.setAlignment(alignmet);

    return paragraph;
  }

  private PdfPCell cell(String text, int alignment) {
    Font font = new Font(Font.FontFamily.COURIER, 10);
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setHorizontalAlignment(alignment);
    return cell;
  }

}
