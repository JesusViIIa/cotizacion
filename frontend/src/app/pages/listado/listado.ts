import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
  import { MatSidenavModule } from '@angular/material/sidenav';
import { ListadoService } from './listado.service';
import { DecimalPipe } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Moto } from '../../interfaces/Moto';
import { DetallesCotizacion } from '../detalles-cotizacion/detalles-cotizacion';

@Component({
  selector: 'app-listado',
  imports: [MatSidenavModule, MatButtonModule, DecimalPipe, RouterOutlet, DetallesCotizacion],
  templateUrl: './listado.html',
  styleUrl: './listado.css',
})
export class Listado {
  showFiller = false;

  constructor(public listadoService: ListadoService) {
    this.listadoService.loadMotos();
    this.listadoService.loadAccesorios();
    
  }



  selectMoto(moto: Moto, drawer: any) {
    console.log("Selcting moto", moto)
    this.listadoService.selectMoto(moto);
    drawer.toggle();
  }

}
