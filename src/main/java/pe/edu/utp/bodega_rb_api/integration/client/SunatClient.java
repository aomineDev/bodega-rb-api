package pe.edu.utp.bodega_rb_api.integration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.bodega_rb_api.integration.dto.SunatCustomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SunatClient {

  @Value("${api.token}")
  private String token;
  private static final String BASE_URL = "https://api.decolecta.com/v1/";

  public SunatCustomer getCustomer(String ruc) throws Exception {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "sunat/ruc/full?numero=" + ruc))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + token)
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return new ObjectMapper().readValue(response.body(), SunatCustomer.class);
  }
}