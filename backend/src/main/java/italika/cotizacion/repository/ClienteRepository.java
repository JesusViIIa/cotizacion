package italika.cotizacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import italika.cotizacion.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Optional<Cliente> findByCorreo(String emailString);

}