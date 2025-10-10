package pe.edu.utp.bodega_rb_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.bodega_rb_api.model.AuditoriaInventario;
import pe.edu.utp.bodega_rb_api.repository.AuditoriaInventarioRepository;
import pe.edu.utp.bodega_rb_api.service.AuditoriaInventarioService;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaInventarioServiceImpl implements AuditoriaInventarioService {

    @Autowired
    private AuditoriaInventarioRepository auditoriaInventarioRepository;

    @Override
    public AuditoriaInventario save(AuditoriaInventario auditoriaInventario) {
        return auditoriaInventarioRepository.save(auditoriaInventario);
    }

    @Override
    public List<AuditoriaInventario> findAll() {
        return auditoriaInventarioRepository.findAll();
    }

    @Override
    public AuditoriaInventario findById(Integer id) {
        Optional<AuditoriaInventario> auditoria = auditoriaInventarioRepository.findById(id);
        return auditoria.orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        auditoriaInventarioRepository.deleteById(id);
    }
}