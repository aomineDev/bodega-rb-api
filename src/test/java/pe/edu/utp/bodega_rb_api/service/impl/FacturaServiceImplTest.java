package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.model.Factura;
import pe.edu.utp.bodega_rb_api.repository.FacturaRepository;

@ExtendWith(MockitoExtension.class)
class FacturaServiceImplTest {

  @Mock
  private FacturaRepository facturaRepository;

  @InjectMocks
  private FacturaServiceImpl facturaService;

  @Test
  void findAll_DeberiaRetornarListaDeFacturas() {
    Factura factura1 = new Factura();
    factura1.setId(1);
    Factura factura2 = new Factura();
    factura2.setId(2);
    when(facturaRepository.findAll()).thenReturn(Arrays.asList(factura1, factura2));

    List<Factura> resultado = facturaService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarFactura_CuandoExiste() {
    Integer id = 1;
    Factura factura = new Factura();
    factura.setId(id);
    when(facturaRepository.findById(id)).thenReturn(Optional.of(factura));

    Optional<Factura> resultado = facturaService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarFactura() {
    Factura nuevaFactura = new Factura();
    Factura facturaGuardada = new Factura();
    facturaGuardada.setId(1);

    when(facturaRepository.save(any(Factura.class))).thenReturn(facturaGuardada);

    Factura resultado = facturaService.save(nuevaFactura);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(facturaRepository).save(any(Factura.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    facturaService.deleteById(id);
    verify(facturaRepository).deleteById(id);
  }
}
