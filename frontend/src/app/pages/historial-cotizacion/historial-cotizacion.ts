import { Component } from '@angular/core';
import { ListadoService } from '../listado/listado.service';
import { DatePipe, DecimalPipe } from '@angular/common';
import { MatAnchor } from "@angular/material/button";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-historial-cotizacion',
  imports: [DecimalPipe, DatePipe, MatAnchor, RouterLink],
  templateUrl: './historial-cotizacion.html',
  styleUrl: './historial-cotizacion.css',
})
export class HistorialCotizacion {


  constructor(public listadoService: ListadoService) {
    listadoService.loadCotizaciones();
  }

}
