import { Component } from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Header } from '../header/header';
import { Listado } from '../listado/listado';

@Component({
  selector: 'app-cotizacion',
  imports: [MatSlideToggleModule, Header, Listado],
  templateUrl: './cotizacion.html',
  styleUrl: './cotizacion.css',
})
export class Cotizacion {

}
