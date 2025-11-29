package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.model.Caja;
import pe.edu.utp.bodega_rb_api.repository.CajaRepository;
import pe.edu.utp.bodega_rb_api.service.CajaService;

@Service
public class CajaServiceImpl implements CajaService {
  @Autowired
  private CajaRepository repository;

  @Override
  public List<Caja> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Caja> findById(Integer id) {
    return repository.findById(id);
  }

  @Override
  public Caja save(Caja entity) {

    entity.setFechaApertura(LocalDate.now());
    entity.setHoraApertura(LocalTime.now());
    entity.setEstado(true);

    return repository.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<Caja> findCajaAbierta() {
    return repository.findByEstadoTrue();
  }
}