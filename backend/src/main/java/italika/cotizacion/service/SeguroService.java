package italika.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italika.cotizacion.entity.Seguro;
import italika.cotizacion.repository.SeguroRepository;
import italika.cotizacion.service.interfaces.ISeguroService;

@Service
public class SeguroService implements ISeguroService {

    @Autowired
    SeguroRepository seguroRepository;

    @Override
    public List<Seguro> findAll() {
        return seguroRepository.findAll();
    }

    

}
