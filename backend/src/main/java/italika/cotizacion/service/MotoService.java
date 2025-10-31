package italika.cotizacion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italika.cotizacion.entity.Moto;
import italika.cotizacion.repository.MotoRepository;
import italika.cotizacion.service.interfaces.IMotoService;


@Service
public class MotoService implements IMotoService {

    @Autowired
    MotoRepository motoRepository;



    @Override
    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    @Override
    public Moto findById(Integer id) {
        return motoRepository.findById(id).orElse(null);
    }
    

    @Override
    public void save(Moto moto) {
        motoRepository.save(moto);
    }

    @Override
    public void delete(Integer id) {
        motoRepository.deleteById(id);;
    }


    

}
