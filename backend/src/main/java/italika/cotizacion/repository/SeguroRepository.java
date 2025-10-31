package italika.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italika.cotizacion.entity.Seguro;

public interface SeguroRepository extends JpaRepository<Seguro, Integer> {}