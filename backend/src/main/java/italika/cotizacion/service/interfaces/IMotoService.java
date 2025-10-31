package italika.cotizacion.service.interfaces;

import java.util.List;


import italika.cotizacion.entity.Moto;

public interface IMotoService {
    public List<Moto> findAll();
    public Moto findById(Integer id);
    public void save(Moto moto);
    public void delete(Integer  id);
}
