import { Component } from '@angular/core';
import { ListadoService } from '../listado/listado.service';
import { DatePipe, DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-historial-cotizacion',
  imports: [DecimalPipe, DatePipe],
  templateUrl: './historial-cotizacion.html',
  styleUrl: './historial-cotizacion.css',
})
export class HistorialCotizacion {


  constructor(public listadoService: ListadoService) {
    listadoService.loadCotizaciones();
  }

}
