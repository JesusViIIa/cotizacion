package italika.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italika.cotizacion.entity.Accesorio;
import italika.cotizacion.repository.AccesorioRepository;
import italika.cotizacion.service.interfaces.IAccesorioService;


@Service
public class AccesorioService implements IAccesorioService {


    @Autowired
    AccesorioRepository accesorioRepository;

   @Override
    public List<Accesorio> findAll() {
        return accesorioRepository.findAll();
    }

}
