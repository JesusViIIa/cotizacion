package italika.cotizacion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoResponseDto {
    Integer id;
    String modelo, version, precio;

}
