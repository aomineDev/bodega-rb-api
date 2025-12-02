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

import pe.edu.utp.bodega_rb_api.model.IngresoProducto;
import pe.edu.utp.bodega_rb_api.repository.IngresoProductoRepository;

@ExtendWith(MockitoExtension.class)
class IngresoProductoServiceImplTest {

  @Mock
  private IngresoProductoRepository ingresoProductoRepository;

  @InjectMocks
  private IngresoProductoServiceImpl ingresoProductoService;

  @Test
  void findAll_DeberiaRetornarListaDeIngresosProducto() {
    IngresoProducto ingreso1 = new IngresoProducto();
    ingreso1.setId(1);
    IngresoProducto ingreso2 = new IngresoProducto();
    ingreso2.setId(2);
    when(ingresoProductoRepository.findAll()).thenReturn(Arrays.asList(ingreso1, ingreso2));

    List<IngresoProducto> resultado = ingresoProductoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarIngresoProducto_CuandoExiste() {
    Integer id = 1;
    IngresoProducto ingreso = new IngresoProducto();
    ingreso.setId(id);
    when(ingresoProductoRepository.findById(id)).thenReturn(Optional.of(ingreso));

    Optional<IngresoProducto> resultado = ingresoProductoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarIngresoProducto() {
    IngresoProducto nuevoIngreso = new IngresoProducto();
    IngresoProducto ingresoGuardado = new IngresoProducto();
    ingresoGuardado.setId(1);

    when(ingresoProductoRepository.save(any(IngresoProducto.class))).thenReturn(ingresoGuardado);

    IngresoProducto resultado = ingresoProductoService.save(nuevoIngreso);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(ingresoProductoRepository).save(any(IngresoProducto.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    ingresoProductoService.deleteById(id);
    verify(ingresoProductoRepository).deleteById(id);
  }
}
