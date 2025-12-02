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

import pe.edu.utp.bodega_rb_api.model.Boleta;
import pe.edu.utp.bodega_rb_api.repository.BoletaRepository;

@ExtendWith(MockitoExtension.class)
class BoletaServiceImplTest {

  @Mock
  private BoletaRepository boletaRepository;

  @InjectMocks
  private BoletaServiceImpl boletaService;

  @Test
  void findAll_DeberiaRetornarListaDeBoletas() {
    Boleta boleta1 = new Boleta();
    boleta1.setId(1);
    Boleta boleta2 = new Boleta();
    boleta2.setId(2);
    when(boletaRepository.findAll()).thenReturn(Arrays.asList(boleta1, boleta2));

    List<Boleta> resultado = boletaService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarBoleta_CuandoExiste() {
    Integer id = 1;
    Boleta boleta = new Boleta();
    boleta.setId(id);
    when(boletaRepository.findById(id)).thenReturn(Optional.of(boleta));

    Optional<Boleta> resultado = boletaService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarBoleta() {
    Boleta nuevaBoleta = new Boleta();
    Boleta boletaGuardada = new Boleta();
    boletaGuardada.setId(1);

    when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaGuardada);

    Boleta resultado = boletaService.save(nuevaBoleta);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(boletaRepository).save(any(Boleta.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    boletaService.deleteById(id);
    verify(boletaRepository).deleteById(id);
  }
}
