package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.bodega_rb_api.model.ClienteNatural;
import pe.edu.utp.bodega_rb_api.repository.ClienteNaturalRepository;

@ExtendWith(MockitoExtension.class)
class ClienteNaturalServiceImplTest {

  @Mock
  private ClienteNaturalRepository clienteNaturalRepository;

  @InjectMocks
  private ClienteNaturalServiceImpl clienteNaturalService;

  @Test
  void findAll_DeberiaRetornarListaDeClientesNaturales() {
    ClienteNatural cliente1 = new ClienteNatural();
    cliente1.setId(1);
    ClienteNatural cliente2 = new ClienteNatural();
    cliente2.setId(2);
    when(clienteNaturalRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

    List<ClienteNatural> resultado = clienteNaturalService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarClienteNatural_CuandoExiste() {
    Integer id = 1;
    ClienteNatural cliente = new ClienteNatural();
    cliente.setId(id);
    when(clienteNaturalRepository.findById(id)).thenReturn(Optional.of(cliente));

    Optional<ClienteNatural> resultado = clienteNaturalService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarClienteNatural_YEstablecerFechaRegistro() {
    ClienteNatural nuevoCliente = new ClienteNatural();

    ClienteNatural clienteGuardado = new ClienteNatural();
    clienteGuardado.setId(1);
    clienteGuardado.setFechaRegistro(LocalDate.now());

    when(clienteNaturalRepository.save(any(ClienteNatural.class))).thenReturn(clienteGuardado);

    ClienteNatural resultado = clienteNaturalService.save(nuevoCliente);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getFechaRegistro()).isNotNull();
    verify(clienteNaturalRepository).save(any(ClienteNatural.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    clienteNaturalService.deleteById(id);
    verify(clienteNaturalRepository).deleteById(id);
  }
}
