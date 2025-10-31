import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./pages/cotizacion/cotizacion').then(m => m.Cotizacion),
    }
];
    