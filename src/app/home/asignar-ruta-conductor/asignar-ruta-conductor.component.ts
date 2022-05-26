import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

export interface DatosTablaRutaConductor {
  codigo: number;
  ruta: string;
  conductor: string;
}
const ELEMENT_DATA: DatosTablaRutaConductor[] = [
  {codigo: 1, ruta:'Ruta 1: Umuto - Incho', conductor: 'Karina Perez'},
  {codigo: 2, ruta:'Ruta 8: Chacra Vieja', conductor: 'Maria Torres'},
  {codigo: 3, ruta:'Ruta 9: Batanyacu - Polideportivo', conductor: 'Jose Suarez'},
];

@Component({
  selector: 'app-asignar-ruta-conductor',
  templateUrl: './asignar-ruta-conductor.component.html',
  styleUrls: ['./asignar-ruta-conductor.component.css']
})
export class AsignarRutaConductorComponent implements OnInit {
  displayedColumns: string[] = ['codigo', 'ruta', 'conductor', 'acciones'];
  searchForm: any;
  dataSource = ELEMENT_DATA;
  constructor() { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      selectruta: new FormControl(null),
      selectconductor: new FormControl(null)
    });
  }

}
