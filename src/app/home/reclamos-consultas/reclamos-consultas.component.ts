import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';

/* TABLA RECLAMOS CLIENTE */

export interface DatosTablaReclamo {
  r_fechareclamo: string;
  r_ciudadano: string;
  r_reclamo: string;
}
const ELEMENT_DATA_RECLAMO: DatosTablaReclamo[] = [
  {r_fechareclamo: '05/30/2022', r_ciudadano:'Alexander Carvajal Vargas', r_reclamo: 'detalle del reclamo'},
];

/* TABLA DENUNCIAS */

export interface DatosTablaDenuncia {
  d_fechadenuncia: string;
  d_tipodenuncia: string;
  d_descripciondenuncia: string;
  d_estado: string;
  d_nombrecompleto: string;
}
const ELEMENT_DATA_DENUNCIA: DatosTablaDenuncia[] = [
  {d_fechadenuncia: '30/05/2022', d_tipodenuncia:'Publica', d_descripciondenuncia: 'descripcion de la denuncia', d_estado: 'Nuevo', d_nombrecompleto: 'Alexander Carvajal Vargas'},
];

/* TABLA RECLAMOS CONDUCTOR */

export interface DatosTablaRConductor {
  rc_fechareclamo: string;
  rc_conductor: string;
  rc_reclamo: string;
}
const ELEMENT_DATA_RECLAMO_CONDUCTOR: DatosTablaRConductor[] = [
  {rc_fechareclamo: '05/30/2022', rc_conductor:'Abel Ruiz Diaz', rc_reclamo: 'detalle del reclamo'},
];


@Component({
  selector: 'app-reclamos-consultas',
  templateUrl: './reclamos-consultas.component.html',
  styleUrls: ['./reclamos-consultas.component.css']
})
export class ReclamosConsultasComponent implements OnInit {
  searchForm: any;
  searchFormDenuncia: any;
  searchFormRConduc: any;
  dataSource = ELEMENT_DATA_RECLAMO;
  dataSourceDenuncia = ELEMENT_DATA_DENUNCIA;
  dataSourceRConductor = ELEMENT_DATA_RECLAMO_CONDUCTOR;
  displayedColumns: string[] = ['r_fechareclamo', 'r_ciudadano', 'r_reclamo', 'acciones'];
  displayedColumnsRConductor: string[] = ['rc_fechareclamo', 'rc_conductor', 'rc_reclamo', 'acciones'];
  displayedColumnsDemanda: string[] = ['d_fechadenuncia', 'd_tipodenuncia', 'd_descripciondenuncia', 'd_estado', 'd_nombrecompleto','acciones'];
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });

  constructor() { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      selectestado: new FormControl(null),
    });
    this.searchFormDenuncia = new FormGroup({
      selectTipoD: new FormControl(null),
      selectEstadoD: new FormControl(null),
    });
    this.searchFormRConduc = new FormGroup({
      selectestadoC: new FormControl(null),
    });
  }
  clearForm(form: FormGroup, control: string) {
    form.get(control)?.setValue('');
  }
  clearrangeDate(form: FormGroup, inicio: string, fin: string) {
    form.get(inicio)?.setValue('');
    form.get(fin)?.setValue('');
  }

}
