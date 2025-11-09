package pe.edu.utp.bodega_rb_api.config;

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
        root.setDireccion(null);
        root.setImagen("/img/default.png");
        root.setFechaNacimiento(null);
        root.setEmail(null);
        root.setTelefono(null);
        root.setClave(passwordEncoder.encode("root"));
        root.setRol(rolAdmin);
        empleadoRepository.save(root);
      }

    }
  }
}