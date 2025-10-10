package pe.edu.utp.bodega_rb_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.bodega_rb_api.model.DetalleAuditoria;
import pe.edu.utp.bodega_rb_api.repository.DetalleAuditoriaRepository;
import pe.edu.utp.bodega_rb_api.service.DetalleAuditoriaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleAuditoriaServiceImpl implements DetalleAuditoriaService {

    @Autowired
    private DetalleAuditoriaRepository detalleAuditoriaRepository;

    @Override
    public DetalleAuditoria save(DetalleAuditoria detalleAuditoria) {
        return detalleAuditoriaRepository.save(detalleAuditoria);
    }

    @Override
    public List<DetalleAuditoria> findAll() {
        return detalleAuditoriaRepository.findAll();
    }

    @Override
    public DetalleAuditoria findById(Integer id) {
        Optional<DetalleAuditoria> detalle = detalleAuditoriaRepository.findById(id);
        return detalle.orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        detalleAuditoriaRepository.deleteById(id);
    }
}