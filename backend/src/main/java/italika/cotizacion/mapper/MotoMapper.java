package italika.cotizacion.mapper;

import italika.cotizacion.dto.request.MotoRequestDto;
import italika.cotizacion.entity.Moto;

public class MotoMapper {
    public Moto toEntity(MotoRequestDto dto){
        if (dto == null) {
            return null;
        }

        Moto moto = new Moto();
        moto.setModelo(dto.getModelo());
        moto.setVersion(dto.getVersion());
        moto.setPrecio(dto.getPrecio());
        return moto;



    }

}
