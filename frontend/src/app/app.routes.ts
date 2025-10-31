import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./pages/cotizacion/cotizacion').then(m => m.Cotizacion),
    },
    {
        path: 'historial',
        loadComponent: () => import('./pages/historial-cotizacion/historial-cotizacion').then(m => m.HistorialCotizacion),
    }
];
    