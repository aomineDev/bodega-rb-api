package pe.edu.utp.bodega_rb_api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pe.edu.utp.bodega_rb_api.model.Rol;
import pe.edu.utp.bodega_rb_api.repository.RolRepository;
import pe.edu.utp.bodega_rb_api.util.enums.RolEnum;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
  private final RolRepository rolRepository;

  @Override
  @Transactional
  public void run(String... args) {
    if (!rolRepository.existsByNombreRol(RolEnum.ADMINISTRADOR.getName())) {
      for (RolEnum rol : RolEnum.values()) {
        Rol r = new Rol();
        r.setNombre(rol.getName());
        rolRepository.save(r);
      }
    }
  }
}