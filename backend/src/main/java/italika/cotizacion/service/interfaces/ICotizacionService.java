package italika.cotizacion.service.interfaces;

import java.util.List;

import italika.cotizacion.entity.Cliente;
import italika.cotizacion.entity.Cotizacion;
import italika.cotizacion.entity.Moto;

public interface ICotizacionService {
    public Cotizacion create(Integer Idmoto, Cliente datos,  List<Integer> IdAccesorios, Integer IdSeguro, Double enganche, Integer plazoMeses);
    public List<Cotizacion> listAll();
    public void delete(Integer id);

}
