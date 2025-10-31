package italika.cotizacion.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italika.cotizacion.entity.Accesorio;
import italika.cotizacion.entity.Cliente;
import italika.cotizacion.entity.Cotizacion;
import italika.cotizacion.entity.Moto;
import italika.cotizacion.entity.Seguro;
import italika.cotizacion.exception.BadRequestException;
import italika.cotizacion.repository.AccesorioRepository;
import italika.cotizacion.repository.ClienteRepository;
import italika.cotizacion.repository.CotizacionRepository;
import italika.cotizacion.repository.MotoRepository;
import italika.cotizacion.repository.SeguroRepository;
import italika.cotizacion.service.interfaces.ICotizacionService;

@Service
public class CotizacionService implements ICotizacionService {

    @Autowired
    MotoRepository motoRepository;
    @Autowired
    SeguroRepository seguroRepository;
    @Autowired
    AccesorioRepository accesorioRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    CotizacionRepository cotizacionRepository;

    @Override
    public Cotizacion create(Integer Idmoto, Cliente datos, List<Integer> IdAccesorios, Integer IdSeguro,
            Double enganche,
            Integer plazoMeses) {
                
                // Por cuestiones de tiempo no instale Spring Validation
        if(Idmoto == null)throw new  BadRequestException("Elige una moto para continuar con la cotización");
        if(datos == null)throw new  BadRequestException("Los datos de cliente son obligatorios");
        if(enganche == null)throw new  BadRequestException("Ingresa el monto de enganche");

        System.out.println("Id moto" + Idmoto);
        Moto moto = motoRepository.findById(Idmoto).orElse(null);
        if (moto == null) {
            throw new BadRequestException("Elige una moto para continuar con la cotización");
        }

        Seguro seguro = null;
        if(IdSeguro != null){
           seguro = seguroRepository.findById(IdSeguro).orElse(null);
        }

        List<Accesorio> accesorios = null;
        if (IdAccesorios != null && !IdAccesorios.isEmpty()) {
            accesorios = accesorioRepository.findAllById(IdAccesorios);
        }


        Cliente cliente;
        Cliente savedCliente = clienteRepository.findByCorreo(datos.getCorreo()).orElse(null);

        if(savedCliente == null){
            cliente = clienteRepository.save(datos);
        }else{
            cliente = savedCliente;
        }

        Cotizacion cotizacion = calcularCotizacion(moto, seguro, accesorios, enganche, plazoMeses);
        cotizacion.setCliente(cliente);

        cotizacionRepository.save(cotizacion);
        return cotizacion;
    }

    @Override
    public List<Cotizacion> listAll() {
        return cotizacionRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        cotizacionRepository.deleteById(id);
    }

    private Cotizacion calcularCotizacion(
            Moto moto,
            Seguro seguro,
            List<Accesorio> accesorios,
            Double engancheIngresado,
            Integer plazoMeses) {
            

        Cotizacion cotizacion = new Cotizacion();

        double precioSinIva = moto.getPrecio();
        double iva = precioSinIva * 0.16;
        double precioConIva = precioSinIva + iva;
        double costoSeguro = 0;
        if (seguro != null) {
            costoSeguro = seguro.getPrecio();
        }
        // No se define pero voy a agregar 5% del costo con Iva
        double gastosAdministrativos = precioConIva * 0.05;

        double costoAccesorios = 0;
        if (accesorios != null && !accesorios.isEmpty()) {
            for (Accesorio accesorio : accesorios) {
                costoAccesorios += accesorio.getPrecio();
            }
        }

        double costoTotal = precioConIva + gastosAdministrativos + costoAccesorios + costoSeguro;

        

        double engancheMinimo = costoTotal * 0.10;
        if(engancheIngresado < engancheMinimo){
            throw new BadRequestException("El enganche debe ser al menos 10% del costo total ");
        }
        double montoEnganche = (engancheIngresado != null && engancheIngresado >= engancheMinimo)
                ? engancheIngresado
                : engancheMinimo;

        // Cálculo del financiamiento
        double mensualidad = 0;
        if (plazoMeses != null && plazoMeses > 0) {
            double montoFinanciar = costoTotal - montoEnganche;
            double tasaMensual = 0.125 / 12.0; // 12.5% anual

            // Fórmula de anualidad: pago = P * [r(1+r)^n] / [(1+r)^n - 1]
            mensualidad = montoFinanciar *
                    (tasaMensual * Math.pow(1 + tasaMensual, plazoMeses)) /
                    (Math.pow(1 + tasaMensual, plazoMeses) - 1);
        }

        cotizacion.setPrecioSinIVA(precioSinIva);
        cotizacion.setPrecioConIVA(precioConIva);
        cotizacion.setCostoAccesorios(costoAccesorios);
        cotizacion.setGastosAdministrativos(gastosAdministrativos);
        cotizacion.setCostoTotal(costoTotal);
        cotizacion.setMontoEnganche(montoEnganche);
        cotizacion.setPlazo(plazoMeses);
        cotizacion.setMensualidad(mensualidad);
        cotizacion.setFechaCotizacion(LocalDate.now());
        cotizacion.setMoto(moto);
        cotizacion.setSeguroVehiculo(seguro);
        cotizacion.setAccesorios(accesorios);

        return cotizacion;
    }

}
