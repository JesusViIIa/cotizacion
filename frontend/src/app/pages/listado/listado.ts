import { Component, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
  import { MatSidenavModule } from '@angular/material/sidenav';
import { ListadoService } from './listado.service';
import { DecimalPipe } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Accesorio, Moto } from '../../interfaces/Moto';
import { DetallesCotizacion } from '../detalles-cotizacion/detalles-cotizacion';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-listado',
  imports: [MatSidenavModule, MatButtonModule, DecimalPipe, MatButtonToggleModule, DetallesCotizacion, MatIconModule],
  templateUrl: './listado.html',
  styleUrl: './listado.css',
})
export class Listado {
  showFiller = false;
  mode: "motos" | "accesorios" = "motos";
  modeSignal = signal<"motos" | "accesorios">("motos");

  constructor(public listadoService: ListadoService) {
    this.listadoService.loadMotos();
    this.listadoService.loadAccesorios();
    
  }



  selectMoto(moto: Moto, drawer: any) {
    console.log("Selcting moto", moto)
    this.listadoService.selectMoto(moto);
    drawer.toggle();
  }

  selectAccesorio(accesorio: Accesorio, drawer: any) {
    this.listadoService.selectAccesorio(accesorio);
    drawer.toggle();
  }

   quitarAccesorio(accesorio: Accesorio, drawer: any) {
    this.listadoService.quitarAccesorio(accesorio);
    drawer.toggle();
  }



  isSelected(accesorio: Accesorio): boolean {
    const selectedAccesorios = this.listadoService.getSelectedAccesorios()();
    return selectedAccesorios ? selectedAccesorios.some(a => a.id === accesorio.id) : false;
  }

}
