import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/components/login/login.component';
import { DashboardComponent } from 'src/app/home/dashboard/dashboard.component';
import { RegistrarConductorComponent } from 'src/app/home/registrar-conductor/registrar-conductor.component'
import { AsignarConductorCarroComponent } from 'src/app/home/asignar-conductor-carro/asignar-conductor-carro.component'

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login', pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'registrar-conductor',
    component: RegistrarConductorComponent
  },
  {
    path: 'asignar-conductor-carro',
    component: AsignarConductorCarroComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
