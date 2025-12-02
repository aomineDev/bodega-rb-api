package pe.edu.utp.bodega_rb_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.repository.EmpleadoRepository;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

  @Mock
  private EmpleadoRepository empleadoRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private EmpleadoServiceImpl empleadoService;

  @Test
  void findAll_DeberiaRetornarListaDeEmpleados() {
    Empleado empleado1 = new Empleado();
    empleado1.setId(1);
    Empleado empleado2 = new Empleado();
    empleado2.setId(2);
    when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado1, empleado2));

    List<Empleado> resultado = empleadoService.findAll();

    assertThat(resultado).hasSize(2);
  }

  @Test
  void findById_DeberiaRetornarEmpleado_CuandoExiste() {
    Integer id = 1;
    Empleado empleado = new Empleado();
    empleado.setId(id);
    when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));

    Optional<Empleado> resultado = empleadoService.findById(id);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getId()).isEqualTo(id);
  }

  @Test
  void findByRol_Nombre_DeberiaRetornarListaDeEmpleados() {
    String rolNombre = "ADMIN";
    Empleado empleado1 = new Empleado();
    when(empleadoRepository.findByRol_Nombre(rolNombre)).thenReturn(Arrays.asList(empleado1));

    List<Empleado> resultado = empleadoService.findByRol_Nombre(rolNombre);

    assertThat(resultado).hasSize(1);
  }

  @Test
  void findByDni_DeberiaRetornarEmpleado_CuandoExiste() {
    String dni = "12345678";
    Empleado empleado = new Empleado();
    empleado.setDni(dni);
    when(empleadoRepository.findByDni(dni)).thenReturn(Optional.of(empleado));

    Optional<Empleado> resultado = empleadoService.findByDni(dni);

    assertThat(resultado).isPresent();
    assertThat(resultado.get().getDni()).isEqualTo(dni);
  }

  @Test
  void save_DeberiaGuardarNuevoEmpleado_YEncriptarClave() {
    Empleado nuevoEmpleado = new Empleado();
    nuevoEmpleado.setClave("password123");

    Empleado empleadoGuardado = new Empleado();
    empleadoGuardado.setId(1);
    empleadoGuardado.setClave("encodedPassword");

    when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
    when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoGuardado);

    Empleado resultado = empleadoService.save(nuevoEmpleado);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getClave()).isEqualTo("encodedPassword");
    verify(passwordEncoder).encode("password123");
    verify(empleadoRepository).save(any(Empleado.class));
  }

  @Test
  void save_DeberiaActualizarEmpleado_SinCambiarClave_SiClaveEsNula() {
    Integer id = 1;
    Empleado empleadoExistente = new Empleado();
    empleadoExistente.setId(id);
    empleadoExistente.setClave("oldEncodedPassword");

    Empleado empleadoActualizado = new Empleado();
    empleadoActualizado.setId(id);
    empleadoActualizado.setClave(null); // No password change

    when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleadoExistente));
    when(empleadoRepository.save(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Empleado resultado = empleadoService.save(empleadoActualizado);

    assertThat(resultado.getClave()).isEqualTo("oldEncodedPassword");
    verify(empleadoRepository).save(any(Empleado.class));
  }

  @Test
  void save_DeberiaActualizarEmpleado_YEncriptarNuevaClave() {
    Integer id = 1;
    Empleado empleadoExistente = new Empleado();
    empleadoExistente.setId(id);
    empleadoExistente.setClave("oldEncodedPassword");

    Empleado empleadoActualizado = new Empleado();
    empleadoActualizado.setId(id);
    empleadoActualizado.setClave("newPassword");

    when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleadoExistente));
    when(passwordEncoder.encode("newPassword")).thenReturn("newEncodedPassword");
    when(empleadoRepository.save(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Empleado resultado = empleadoService.save(empleadoActualizado);

    assertThat(resultado.getClave()).isEqualTo("newEncodedPassword");
    verify(passwordEncoder).encode("newPassword");
    verify(empleadoRepository).save(any(Empleado.class));
  }

  @Test
  void updatePassword_DeberiaActualizarClave_CuandoClaveActualEsCorrecta() {
    Integer id = 1;
    String currentPassword = "oldPassword";
    String newPassword = "newPassword";

    Empleado empleado = new Empleado();
    empleado.setId(id);
    empleado.setClave("encodedOldPassword");

    when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));
    when(passwordEncoder.matches(currentPassword, "encodedOldPassword")).thenReturn(true);
    when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");
    when(empleadoRepository.save(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Empleado resultado = empleadoService.updatePassword(id, currentPassword, newPassword);

    assertThat(resultado.getClave()).isEqualTo("encodedNewPassword");
  }

  @Test
  void updatePassword_DeberiaLanzarExcepcion_CuandoClaveActualEsIncorrecta() {
    Integer id = 1;
    String currentPassword = "wrongPassword";
    String newPassword = "newPassword";

    Empleado empleado = new Empleado();
    empleado.setId(id);
    empleado.setClave("encodedOldPassword");

    when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));
    when(passwordEncoder.matches(currentPassword, "encodedOldPassword")).thenReturn(false);

    assertThatThrownBy(() -> empleadoService.updatePassword(id, currentPassword, newPassword))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Contraseña actual incorrecta");
  }

  @Test
  void deleteById_DeberiaLlamarRepositorio() {
    Integer id = 1;
    empleadoService.deleteById(id);
    verify(empleadoRepository).deleteById(id);
  }
}
