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

import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
class RolServiceImplTest {

  @Mock
  private RolRepository rolRepository;

  @InjectMocks
  private RolServiceImpl rolService;

  @Test
  void findAll_DeberiaRetornarListaDeRoles() {
    Rol rol1 = new Rol(1, "ROLE_ADMINISTRADOR");
    Rol rol2 = new Rol(2, "ROLE_CAJERO");
    when(rolRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));

    List<Rol> resultado = rolService.findAll();

    assertThat(resultado).hasSize(2);
    assertThat(resultado.get(0).getNombre()).isEqualTo("ROLE_ADMINISTRADOR");
    assertThat(resultado.get(1).getNombre()).isEqualTo("ROLE_CAJERO");
  }

  @Test
  void findById_DeberiaRetornarRol_CuandoExiste() {
    Integer id = 1;
    Rol rol = new Rol(id, "ROLE_ADMINISTRADOR");
    when(rolRepository.findById(id)).thenReturn(Optional.of(rol));

    Optional<Rol> resultado = rolService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getNombre()).isEqualTo("ROLE_ADMINISTRADOR");
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void findById_DeberiaRetornarVacio_CuandoNoExiste() {
    Integer id = 999;
    when(rolRepository.findById(id)).thenReturn(Optional.empty());

    Optional<Rol> resultado = rolService.findById(id);

    assertThat(resultado).isEmpty();
  }

  @Test
  void save_DeberiaGuardarRol() {
    Rol nuevoRol = new Rol(null, "ROLE_ASISTENTE");
    Rol rolGuardado = new Rol(3, "ROLE_ASISTENTE");

    when(rolRepository.save(any(Rol.class))).thenReturn(rolGuardado);

    Rol resultado = rolService.save(nuevoRol);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(3);
    assertThat(resultado.getNombre()).isEqualTo("ROLE_ASISTENTE");
    verify(rolRepository).save(any(Rol.class));
  }

  @Test
  void save_DeberiaActualizarRolExistente() {
    Rol rolExistente = new Rol(1, "ROLE_JEFE_ALMACEN");
    when(rolRepository.save(any(Rol.class))).thenReturn(rolExistente);

    Rol resultado = rolService.save(rolExistente);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getNombre()).isEqualTo("ROLE_JEFE_ALMACEN");
    verify(rolRepository).save(any(Rol.class));
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;

    rolService.deleteById(id);

    verify(rolRepository).deleteById(id);
  }
}
