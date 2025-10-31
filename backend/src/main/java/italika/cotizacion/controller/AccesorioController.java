package italika.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italika.cotizacion.entity.Accesorio;
import italika.cotizacion.service.AccesorioService;

@RestController
@RequestMapping("/accesorios")
public class AccesorioController {

 @Autowired
    private AccesorioService accesorioService;

    @GetMapping()
     public List<Accesorio> listAll() {
        return accesorioService.findAll();

    }
}
