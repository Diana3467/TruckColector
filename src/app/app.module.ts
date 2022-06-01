import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegistrarConductorComponent } from './home/registrar-conductor/registrar-conductor.component';
import { AsignarConductorCarroComponent } from './home/asignar-conductor-carro/asignar-conductor-carro.component';
import { AsignarRutaConductorComponent } from './home/asignar-ruta-conductor/asignar-ruta-conductor.component';
import { AsignarHorarioRutaComponent } from './home/asignar-horario-ruta/asignar-horario-ruta.component';
import { MenuComponent } from './components/menu/menu.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './home/dashboard/dashboard.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarConductorComponent,
    AsignarConductorCarroComponent,
    AsignarRutaConductorComponent,
    AsignarHorarioRutaComponent,
    MenuComponent,
    LoginComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatInputModule,
    FormsModule,
    MatTooltipModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSelectModule,
    HttpClientModule,
    MatNativeDateModule,
    ToastrModule.forRoot()
  ],
  providers: [
    MatDatepickerModule,
    MatNativeDateModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
