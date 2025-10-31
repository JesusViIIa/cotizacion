import { Component, signal, Signal, computed, ChangeDetectionStrategy } from '@angular/core';
import { ListadoService } from '../listado/listado.service';
import { Accesorio, Moto, Seguro } from '../../interfaces/Moto';
import { DecimalPipe, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-detalles-cotizacion',
  imports: [JsonPipe, DecimalPipe],
  templateUrl: './detalles-cotizacion.html',
  styleUrl: './detalles-cotizacion.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DetallesCotizacion {
  datosForm = signal({
    nombre: '',
    telefono: '',
    email: ''
  });
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

  costoTotal = computed(() => {
    const moto = this.selectedMoto();
    const seguro = this.selectedSeguro();
    return moto ? moto.precio + this.iva() + this.gastosAdministrativos() + this.costoAccesorios() + (seguro ? seguro.precio : 0) : 0;
  });


  plazoMeses = [24, 36, 48, 60];
  selectedPlazo = signal<number | null>(null);
  montoEngancheMinimo = computed(() => 
    this.selectedPlazo() ? this.costoTotal() * 0.10 : 0
  ); 
  montoEnganche = signal<number>(0);
  mensualidad = computed(() => {
    const plazo = this.selectedPlazo();
    const montoFinanciar = this.costoTotal() - this.montoEnganche();
    const tasaMensual = 0.125 / 12.0; // 12.5% anual

    // Fórmula de anualidad: pago = P * [r(1+r)^n] / [(1+r)^n - 1]
    return plazo && plazo > 0 ? montoFinanciar * (tasaMensual * Math.pow(1 + tasaMensual, plazo)) / (Math.pow(1 + tasaMensual, plazo) - 1) : 0;
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

  selectPlazo(plazo: number | null) {
    this.selectedPlazo.set(plazo);
  }

  selectEnganche(event: any) {
    const value = parseFloat(event.target.value);
    this.montoEnganche.set(value);
  }

  



  setNomnbre(event: any) {
    const value = event.target.value;
    this.datosForm.update(d => ({...d, nombre: value}));
  }
  setTelefono(event: any) {
    const value = event.target.value;
    this.datosForm.update(d => ({...d, telefono: value}));
  }
  setEmail(event: any) {
    const value = event.target.value;
    this.datosForm.update(d => ({...d, email: value}));
  }


  finalizarCotizacion() {
    alert("Cotización finalizada. Gracias por su preferencia.");
  }

}
