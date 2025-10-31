import { Component, signal, Signal, computed } from '@angular/core';
import { ListadoService } from '../listado/listado.service';
import { Accesorio, Moto, Seguro } from '../../interfaces/Moto';
import { DecimalPipe, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-detalles-cotizacion',
  imports: [JsonPipe, DecimalPipe],
  templateUrl: './detalles-cotizacion.html',
  styleUrl: './detalles-cotizacion.css',
})
export class DetallesCotizacion {
  selectedMoto: Signal<Moto | null>;
  selectedAccesorios: Signal<Accesorio[] | null>;
  selectedSeguro: Signal<Seguro | null>;
  iva = computed(() => {
    const moto = this.selectedMoto();
    return moto ? moto.precio * 0.16 : 0;
  });
  // Gastos administrativos 5% del precio de la moto con iva
  gastosAdministrativos = computed(() => {
    const moto = this.selectedMoto();
    return moto ? (moto.precio + this.iva()) * 0.05 : 0;
  });

  costoAccesorios = computed(() => {
    const accesorios = this.selectedAccesorios();
    return accesorios ? accesorios.reduce((total, a) => total + a.precio, 0) : 0;
  });



  constructor(public listadoService: ListadoService) {
    listadoService.loadSeguros();
    this.selectedMoto = this.listadoService.getSelectedMoto();
    this.selectedAccesorios = this.listadoService.getSelectedAccesorios();
    this.selectedSeguro = this.listadoService.getSelectedSeguro();
    console.log("seletedMoto", this.selectedMoto())
  }

  selectSeguro(seguro: Seguro | null) {
    this.listadoService.selectSeguro(seguro);
  }

}
