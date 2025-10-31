package italika.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import italika.cotizacion.entity.Seguro;
import italika.cotizacion.service.SeguroService;


@RestController
@RequestMapping("/seguros")
public class SeguroController {

    @Autowired
    SeguroService seguroService;


     @GetMapping()
     public List<Seguro> listAll() {
        return seguroService.findAll();
    }

}
