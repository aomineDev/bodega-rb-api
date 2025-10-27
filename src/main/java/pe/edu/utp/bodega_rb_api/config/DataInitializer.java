package pe.edu.utp.bodega_rb_api.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.EmpleadoRepository;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;
import pe.edu.utp.bodega_rb_api.util.enums.RolEnum;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final RolRepository rolRepository;
  private final EmpleadoRepository empleadoRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(String... args) {
    if (!rolRepository.existsByNombre(RolEnum.ADMINISTRADOR.getName())) {
      for (RolEnum rol : RolEnum.values()) {
        Rol r = new Rol();
        r.setNombre(rol.getName());
        rolRepository.save(r);
      }

      if (empleadoRepository.count() == 0) {
        Rol rolAdmin = rolRepository.findByNombre(RolEnum.ADMINISTRADOR.getName()).orElseThrow();

        Empleado root = new Empleado();
        root.setDni("00000000");
        root.setNombre("Root");
        root.setApellidoMaterno("");
        root.setApellidoPaterno("");
        root.setDireccion("");
        root.setImagen("");
        root.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        root.setEmail("");
        root.setTelefono("");
        root.setClave(passwordEncoder.encode("root"));
        root.setRolId(rolAdmin);
        empleadoRepository.save(root);
      }

    }
  }
}