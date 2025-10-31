package italika.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italika.cotizacion.entity.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {}