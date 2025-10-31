package italika.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italika.cotizacion.entity.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {}