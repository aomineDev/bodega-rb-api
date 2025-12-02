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

import pe.edu.utp.bodega_rb_api.model.Proveedor;
import pe.edu.utp.bodega_rb_api.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceImplTest {

  @Mock
  private ProveedorRepository proveedorRepository;

  @InjectMocks
  private ProveedorServiceImpl proveedorService;

  @Test
  void findAll_DeberiaRetornarListaDeProveedores() {
    Proveedor p1 = new Proveedor();
    p1.setId(1);
    Proveedor p2 = new Proveedor();
    p2.setId(2);
    when(proveedorRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

    List<Proveedor> resultado = proveedorService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarProveedor_CuandoExiste() {
    Integer id = 1;
    Proveedor p = new Proveedor();
    p.setId(id);
    when(proveedorRepository.findById(id)).thenReturn(Optional.of(p));

    Optional<Proveedor> resultado = proveedorService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void save_DeberiaGuardarProveedor_YEstablecerFechaRegistro() {
    Proveedor nuevo = new Proveedor();
    Proveedor guardado = new Proveedor();
    guardado.setId(1);
    guardado.setFechaRegistro(LocalDate.now());

    when(proveedorRepository.save(any(Proveedor.class))).thenReturn(guardado);

    Proveedor resultado = proveedorService.save(nuevo);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getFechaRegistro()).isNotNull();
    verify(proveedorRepository).save(any(Proveedor.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    proveedorService.deleteById(id);
    verify(proveedorRepository).deleteById(id);
  }
}
