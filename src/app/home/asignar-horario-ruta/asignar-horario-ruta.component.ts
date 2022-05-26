import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

export interface DatosTablaRutaConductor {
  codigo: number;
  ruta: string;
  conductor: string;
  horinit: string;
  horfin: string;
}
const ELEMENT_DATA: DatosTablaRutaConductor[] = [
  {codigo: 1, ruta:'Ruta 1: Umuto - Incho', conductor: 'Karina Perez', horinit:'', horfin:''},
  {codigo: 2, ruta:'Ruta 8: Chacra Vieja', conductor: 'Maria Torres', horinit:'', horfin:''},
  {codigo: 3, ruta:'Ruta 9: Batanyacu - Polideportivo', conductor: 'Jose Suarez', horinit:'', horfin:''},
];

@Component({
  selector: 'app-asignar-horario-ruta',
  templateUrl: './asignar-horario-ruta.component.html',
  styleUrls: ['./asignar-horario-ruta.component.css']
})
export class AsignarHorarioRutaComponent implements OnInit {
  searchForm: any;
  displayedColumns: string[] = ['codigo', 'ruta', 'conductor', 'horinit', 'horfin','acciones'];
  dataSource = ELEMENT_DATA;
  constructor() { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      selectruta: new FormControl(null),
      selectfrecuencia: new FormControl(null)
    });
  }



}
