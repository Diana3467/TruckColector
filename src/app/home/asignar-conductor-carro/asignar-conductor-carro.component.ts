import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

export interface DatosTablaRegistrarConductor {
  codigo: number;
  conductor: string;
  vehiculo: string;
  fecinit: string;
  fecfin: string;
}
const ELEMENT_DATA: DatosTablaRegistrarConductor[] = [
  {codigo: 1, conductor: 'Karina Perez', vehiculo: 'vehivulo 1', fecinit: '', fecfin: ''},
  {codigo: 2, conductor: 'Maria Torres', vehiculo: 'vehivulo 2', fecinit: '', fecfin: ''},
  {codigo: 3, conductor: 'Jose Suarez', vehiculo: 'vehivulo 3', fecinit: '', fecfin: ''},
];

@Component({
  selector: 'app-asignar-conductor-carro',
  templateUrl: './asignar-conductor-carro.component.html',
  styleUrls: ['./asignar-conductor-carro.component.css']
})
export class AsignarConductorCarroComponent implements OnInit {
  displayedColumns: string[] = ['codigo', 'conductor', 'vehiculo', 'fecinit', 'fecfin', 'acciones'];
  dataSource = ELEMENT_DATA;
  disableSelect = new FormControl(false);

  constructor() { }

  ngOnInit(): void {
  }

}
