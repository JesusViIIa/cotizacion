package italika.cotizacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoRequestDto {
    String modelo, version;
    Double precio;
}
