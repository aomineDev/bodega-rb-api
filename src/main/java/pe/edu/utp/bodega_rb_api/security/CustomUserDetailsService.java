package pe.edu.utp.bodega_rb_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Empleado;
import pe.edu.utp.bodega_rb_api.repository.EmpleadoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private EmpleadoRepository repository;

  @Override
  public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
    Empleado empleado = repository.findByDni(dni)
        .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

    return User.builder()
        .username(dni)
        .password(empleado.getClave())
        .authorities(empleado.getRol().getNombre())
        .build();
  }

}
