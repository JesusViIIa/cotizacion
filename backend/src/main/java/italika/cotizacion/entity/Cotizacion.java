package italika.cotizacion.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    LocalDate fechaCotizacion;

    @Column
    Double montoEnganche;

    @Column
    Double precioSinIVA;

    @Column
    Double precioConIVA;

    @Column
    Double gastosAdministrativos;

    @Column
    Double costoAccesorios;

    @Column
    Double costoTotal;

    @Column
    Integer plazo;

    @Column
    Double mensualidad;

    @OneToMany
    List<Accesorio> accesorios;

    @ManyToOne
    Seguro seguroVehiculo;

    @ManyToOne
    Moto moto;

    @ManyToOne
    Cliente cliente;

}
