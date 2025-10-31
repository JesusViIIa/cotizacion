package italika.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italika.cotizacion.entity.Moto;
import italika.cotizacion.service.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {


    @Autowired
    private MotoService motoService;

    @GetMapping()
     public List<Moto> listAll() {
        return motoService.findAll();

    }

}
