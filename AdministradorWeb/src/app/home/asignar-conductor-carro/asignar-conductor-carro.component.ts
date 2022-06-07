import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Carro } from '../modelos/carro';
import { Conductor } from '../modelos/conductor';
import { WservConductorCarroService } from './WebServiceConductorCarro/wserv-conductor-carro.service';

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

  itemcarro: Carro[];
  itemconductor: Conductor[];

  displayedColumns: string[] = ['codigo', 'conductor', 'vehiculo', 'fecinit', 'fecfin','acciones'];
  dataSource = ELEMENT_DATA;
  disableSelect = new FormControl(false);
  searchForm: any;

  constructor(
    private WSConductorCarro: WservConductorCarroService,
  ) { }

  ngOnInit(): void {

    this.WSConductorCarro.getlistaconductores().subscribe(item=>{
      this.itemconductor = item;
    });

    this.WSConductorCarro.getlistacarros().subscribe(item=>{
      this.itemcarro = item;
    });


    this.searchForm = new FormGroup({
      selectconductor: new FormControl(null),
      selectvehiculo: new FormControl(null)
    });
  }

}
