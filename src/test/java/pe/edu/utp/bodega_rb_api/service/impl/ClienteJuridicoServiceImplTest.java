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

import pe.edu.utp.bodega_rb_api.model.ClienteJuridico;
import pe.edu.utp.bodega_rb_api.repository.ClienteJuridicoRepository;

@ExtendWith(MockitoExtension.class)
class ClienteJuridicoServiceImplTest {

  @Mock
  private ClienteJuridicoRepository clienteJuridicoRepository;

  @InjectMocks
  private ClienteJuridicoServiceImpl clienteJuridicoService;

  @Test
  void findAll_DeberiaRetornarListaDeClientesJuridicos() {
    ClienteJuridico cliente1 = new ClienteJuridico();
    cliente1.setId(1);
    ClienteJuridico cliente2 = new ClienteJuridico();
    cliente2.setId(2);
    when(clienteJuridicoRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

    List<ClienteJuridico> resultado = clienteJuridicoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarClienteJuridico_CuandoExiste() {
    Integer id = 1;
    ClienteJuridico cliente = new ClienteJuridico();
    cliente.setId(id);
    when(clienteJuridicoRepository.findById(id)).thenReturn(Optional.of(cliente));

    Optional<ClienteJuridico> resultado = clienteJuridicoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarClienteJuridico_YEstablecerFechaRegistro() {
    ClienteJuridico nuevoCliente = new ClienteJuridico();

    ClienteJuridico clienteGuardado = new ClienteJuridico();
    clienteGuardado.setId(1);
    clienteGuardado.setFechaRegistro(LocalDate.now());

    when(clienteJuridicoRepository.save(any(ClienteJuridico.class))).thenReturn(clienteGuardado);

    ClienteJuridico resultado = clienteJuridicoService.save(nuevoCliente);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getFechaRegistro()).isNotNull();
    verify(clienteJuridicoRepository).save(any(ClienteJuridico.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    clienteJuridicoService.deleteById(id);
    verify(clienteJuridicoRepository).deleteById(id);
  }
}
