package pe.edu.utp.bodega_rb_api.service;

import pe.edu.utp.bodega_rb_api.model.ProximoAVencer;

public interface ProximoAVencerService extends ApiService<ProximoAVencer> {
  void handle2x1(Integer id);

  void handleOfferPrice(Integer id, Double precio);
}
