package italika.cotizacion.dto.request;

import java.util.List;

import italika.cotizacion.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CotizacionDto {
    
    Integer idmoto;
    Cliente datos;
    List<Integer> idaccesorios;
    Integer idseguro;
    Double enganche;
    Integer plazomeses;
}
