package italika.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import italika.cotizacion.dto.request.CotizacionDto;
import italika.cotizacion.entity.Cotizacion;
import italika.cotizacion.entity.Moto;
import italika.cotizacion.service.CotizacionService;

@RestController
@RequestMapping("/cotizacion")
public class CotizacionController {

    @Autowired
    CotizacionService cotizacionService;

    @GetMapping()
    public List<Cotizacion> listAll() {
        return cotizacionService.listAll();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable  Integer id) {

         cotizacionService.delete(id);
        return true;
    }

    @PostMapping()
    public Cotizacion create(@RequestBody CotizacionDto body) {
        System.out.println("Cotizacion: " + body.toString());
        return cotizacionService.create(body.getIdmoto(), body.getDatos(), body.getIdaccesorios(), body.getIdseguro(),
                body.getEnganche(), body.getPlazomeses());

    }

}
