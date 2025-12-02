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

import pe.edu.utp.bodega_rb_api.model.Arqueo;
import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.repository.ArqueoRepository;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;

@ExtendWith(MockitoExtension.class)
class ArqueoServiceImplTest {

  @Mock
  private ArqueoRepository arqueoRepository;

  @Mock
  private CajaRepository cajaRepository;

  @InjectMocks
  private ArqueoServiceImpl arqueoService;

  @Test
  void findAll_DeberiaRetornarListaDeArqueos() {
    Arqueo arqueo1 = new Arqueo();
    arqueo1.setId(1);
    Arqueo arqueo2 = new Arqueo();
    arqueo2.setId(2);
    when(arqueoRepository.findAll()).thenReturn(Arrays.asList(arqueo1, arqueo2));

    List<Arqueo> resultado = arqueoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarArqueo_CuandoExiste() {
    Integer id = 1;
    Arqueo arqueo = new Arqueo();
    arqueo.setId(id);
    when(arqueoRepository.findById(id)).thenReturn(Optional.of(arqueo));

    Optional<Arqueo> resultado = arqueoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarArqueo() {
    Caja caja = new Caja();
    caja.setId(1);
    caja.setSaldoActual(100.0);

    Arqueo nuevoArqueo = new Arqueo();
    nuevoArqueo.setCaja(caja);
    nuevoArqueo.setTotalFisico(90.0);

    Arqueo arqueoGuardado = new Arqueo();
    arqueoGuardado.setId(1);
    arqueoGuardado.setCaja(caja);
    arqueoGuardado.setTotalFisico(90.0);
    arqueoGuardado.setTotalSistema(100.0);
    arqueoGuardado.setDiferencia(10.0);

    when(cajaRepository.findById(1)).thenReturn(Optional.of(caja));
    when(arqueoRepository.save(any(Arqueo.class))).thenReturn(arqueoGuardado);

    Arqueo resultado = arqueoService.save(nuevoArqueo);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getTotalSistema()).isEqualTo(100.0);
    // 100 - 90 = 10
    // Note: logic in service is: entity.setDiferencia(entity.getTotalSistema() -
    // entity.getTotalFisico());
    // 100 - 90 = 10.
    verify(cajaRepository).findById(1);
    verify(arqueoRepository).save(any(Arqueo.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    arqueoService.deleteById(id);
    verify(arqueoRepository).deleteById(id);
  }
}
