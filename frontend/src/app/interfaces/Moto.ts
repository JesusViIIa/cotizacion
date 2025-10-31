

export interface Moto {
    id: number; 
    modelo: string;
    version: string;
    precio: number;
}

export interface Accesorio {
    id: number;
    nombre: string;
    descripcion: string;
    precio: number;
}

export interface Seguro {
    id: number;
    nombre: string;
    descripcion: string;
    precio: number;
}


export interface CotizacionDto{
    idmoto: number;
    datos: {
        nombre: string;
        correo: string;
        telefono: string;
    };
    enganche: number;
    idaccesorios: number[];
    idseguro: number | null;
    plazomeses: number | null;

}

export interface CotizacionDto{
    idmoto: number;
    datos: {
        nombre: string;
        correo: string;
        telefono: string;
    };
    enganche: number;
    idaccesorios: number[];
    idseguro: number | null;
    plazomeses: number | null;

}

export interface ICotizacion{
id: number;
fechaCotizacion: string;
montoEnganche: number;
precioSinIVA: number;
precioConIVA: number;
gastosAdministrativos: number;
costoAccesorios: number;
costoTotal: number;
plazo: number;
mensualidad: number;
accesorios: Accesorio[];
seguroVehiculo: Seguro;
moto: Moto;
cliente: ICliente;

}

export interface ICliente {
    id: number;
    nombre: string;
    telefono: string;
    correo: string;
}