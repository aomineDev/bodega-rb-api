package pe.edu.utp.bodega_rb_api.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.integration.client.ReniecClient;
import pe.edu.utp.bodega_rb_api.integration.client.SunatClient;
import pe.edu.utp.bodega_rb_api.integration.dto.ReniecCustomer;
import pe.edu.utp.bodega_rb_api.integration.dto.SunatCustomer;
import pe.edu.utp.bodega_rb_api.model.ClienteJuridico;
import pe.edu.utp.bodega_rb_api.model.ClienteNatural;

@Service
public class IntegrationService {

  @Autowired
  private ReniecClient reniecClient;

  @Autowired
  private SunatClient sunatClient;

  @Autowired
  private ClienteNaturalService clienteNaturalService;

  @Autowired
  private ClienteJuridicoService clienteJuridicoService;

  public ReniecCustomer getCustomerByDni(String dni) throws Exception {
    ReniecCustomer reniecCustomer = reniecClient.getCustomer(dni);
    return reniecCustomer;
  }

  public SunatCustomer getCustomerByRuc(String ruc) throws Exception {
    SunatCustomer sunatCustomer = sunatClient.getCustomer(ruc);
    return sunatCustomer;
  }

  public ClienteNatural createCustomerByDni(String dni) throws Exception {
    ReniecCustomer reniecCustomer = reniecClient.getCustomer(dni);

    if (reniecCustomer == null || reniecCustomer.getDocument_number() == null) {
      throw new Exception("No se encontró información en RENIEC para el DNI: " + dni);
    }

    ClienteNatural clienteNatural = new ClienteNatural();
    clienteNatural.setDni(reniecCustomer.getDocument_number());
    clienteNatural.setNombre(reniecCustomer.getFirst_name());
    clienteNatural.setApellidoPaterno(reniecCustomer.getFirst_last_name());
    clienteNatural.setApellidoMaterno(reniecCustomer.getSecond_last_name());
    clienteNatural.setFechaRegistro(LocalDate.now());

    return clienteNaturalService.save(clienteNatural);
  }

  public ClienteJuridico createCustomerByRuc(String ruc) throws Exception {
    SunatCustomer sunatCustomer = sunatClient.getCustomer(ruc);

    if (sunatCustomer == null || sunatCustomer.getNumero_documento() == null) {
      throw new Exception("No se encontró información en SUNAT para el RUC: " + ruc);
    }

    ClienteJuridico clienteJuridico = new ClienteJuridico();
    clienteJuridico.setRuc(sunatCustomer.getNumero_documento());
    clienteJuridico.setRazonSocial(sunatCustomer.getRazon_social());
    clienteJuridico.setActividadEconomica(sunatCustomer.getActividad_economica());
    clienteJuridico.setDireccion(sunatCustomer.getDireccion());
    clienteJuridico.setTipoContribuyente(sunatCustomer.getTipo());
    clienteJuridico.setFechaRegistro(LocalDate.now());

    return clienteJuridicoService.save(clienteJuridico);
  }

}
