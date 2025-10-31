import { computed, Injectable, signal } from '@angular/core';
import { Accesorio, CotizacionDto, ICotizacion, Moto, Seguro } from '../../interfaces/Moto';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ListadoService {
  // voy aponer la api direct
  private readonly API_URL = environment.apiUrl;
 // Estado interno USANDo Signals en lufgar de BehaviorSubject para simplicidad


 //cotzaciones 
 createCotizacion(CotizacionDto: CotizacionDto){
    return this.http.post<ICotizacion>(`${this.API_URL}/cotizacion`, CotizacionDto);

 }



 //Motos signal
  private motos = signal<Moto[]>([]);
  private loading = signal(false);
  private selectedMoto = signal<Moto | null>(null);
  motosCount = computed(() => this.motos().length);
  isEmpty = computed(() => this.motos().length === 0);

  
  // Accesorios signal
  private accesorios = signal<Accesorio[]>([]);
  private loadingAccesorios = signal(false);
  private selectedAccesorios = signal<Accesorio[]>([]);
  accesoriosCount = computed(() => this.accesorios().length);


  //seguro signal
  readonly seguros = signal<Seguro[]>([]);
  private loadingSeguros = signal(false);
  private selectedSeguro = signal<Seguro | null>(null);
  segurosCount = computed(() => this.seguros().length);







  

  constructor(private http: HttpClient) {}

  loadMotos() {
    this.loading.set(true);
    this.http.get<Moto[]>(`${this.API_URL}/motos`).subscribe({
      next: data => {
        this.motos.set(data);
        this.loading.set(false);
      },
      error: () => this.loading.set(false)
    });
  }

  loadAccesorios() {
    this.loadingAccesorios.set(true);
    this.http.get<Accesorio[]>(`${this.API_URL}/accesorios`).subscribe({
      next: data => {
        this.accesorios.set(data);
        this.loadingAccesorios.set(false);
      },
      error: () => this.loadingAccesorios.set(false)
    });
  }

  loadSeguros() {
    this.loadingSeguros.set(true);
    this.http.get<Seguro[]>(`${this.API_URL}/seguros`).subscribe({
      next: data => {
        this.seguros.set(data);
        this.loadingSeguros.set(false);
      },
      error: () => this.loadingSeguros.set(false)
    });
  }



  selectMoto(moto: Moto) {
    this.selectedMoto.set(moto);
  }


  getMotos = () => this.motos.asReadonly();
  getSelectedMoto = () => this.selectedMoto.asReadonly();
  getLoading = () => this.loading.asReadonly();
  getAccesorios = () => this.accesorios.asReadonly();
  getSelectedAccesorios = () => this.selectedAccesorios.asReadonly();
  getSeguros = () => this.seguros.asReadonly();
  getSelectedSeguro = () => this.selectedSeguro.asReadonly();





  selectSeguro(seguro: Seguro | null) {
    this.selectedSeguro.set(seguro);
  }


selectAccesorio(accesorio: Accesorio) {
    this.selectedAccesorios.update(list => [...list, accesorio]);
  }

  quitarAccesorio(accesorio: Accesorio) {
    this.selectedAccesorios.update(list => list.filter(a => a.id !== accesorio.id));
  }
}