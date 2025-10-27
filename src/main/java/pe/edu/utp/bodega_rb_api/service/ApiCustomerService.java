package pe.edu.utp.bodega_rb_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.integration.api.client.ReniecClient;
import pe.edu.utp.bodega_rb_api.integration.api.client.SunatClient;
import pe.edu.utp.bodega_rb_api.integration.api.dto.ReniecCustomer;
import pe.edu.utp.bodega_rb_api.integration.api.dto.SunatCustomer;

@Service
public class ApiCustomerService {

  @Autowired
  private ReniecClient reniecClient;

  @Autowired
  private SunatClient sunatClient;

  public ReniecCustomer buscarPorDni(String dni) throws Exception {
    return reniecClient.getCustomer(dni);
  }

  public SunatCustomer buscarPorRuc(String ruc) throws Exception {
    return sunatClient.getCustomer(ruc);
  }

}
