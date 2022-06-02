import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Conductor } from '../modelos/conductor';
import { Ruta } from '../modelos/ruta';
import { WservRutConService } from './WebServiceRutCon/wserv-rut-con.service';

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

  itemruta: Ruta[];
  itemconductor: Conductor[];

  respuestaAsignacion:boolean = false;

  fechaini_conver:string;
  fechafin_conver:string;


  displayedColumns: string[] = ['codigo', 'ruta', 'conductor', 'acciones'];
  searchForm: any;
  dataSource = ELEMENT_DATA;
  constructor(
    private http:HttpClient,
    private WebServiceRutaConductor: WservRutConService,
    
  ) { }

  ngOnInit(){

    this.WebServiceRutaConductor.getlistaconductores().subscribe(item=>{
      this.itemconductor = item;
    });

    this.WebServiceRutaConductor.getlistarutas().subscribe(item=>{
      this.itemruta = item;
    });

    this.searchForm = new FormGroup({
      selectruta: new FormControl(null),
      selectconductor: new FormControl(null),
      fecinit: new FormControl(null),
      fecfin: new FormControl(null)
    });
  }

  public AsignarRutaAlConductor(){
    //this.searchForm.value.selectruta.toString();
    //this.searchForm.value.selectconductor.toString();
    //this.searchForm.value.fecinit.toString();
    //this.searchForm.value.fecfin.toString();

    //this.fechaini_conver =  this.searchForm.value.fecinit.transform(Date.now(), 'dd/MM/yyyy').toString();
    //this.fechafin_conver = this.searchForm.value.fecfin.transform(Date.now(), 'dd/MM/yyyy').toString();

    this.fechaini_conver =  this.searchForm.value.fecinit.toString();
    this.fechafin_conver = this.searchForm.value.fecfin.toString();

    //console.log(this.searchForm.value.fecinit.toString());
    //console.log(this.searchForm.value.fecfin.toString());

    console.log(this.fechaini_conver);
    console.log(this.fechafin_conver);


    /*this.WebServiceRutaConductor.AsignarRutaConductor(
      {
        "nCodigoRuta": this.searchForm.value.selectruta.toString(),
        "nCodigoCond": this.searchForm.value.selectconductor.toString(),
        "dFechaInicio": this.searchForm.value.fecinit.toString().transform(Date.now(), 'dd/MM/yyyy'),
        "dFechaFin": this.searchForm.value.fecfin.toString().transform(Date.now(), 'dd/MM/yyyy'),
        "nCodigoAdm": '1',
      }
      ).subscribe(respuesta =>{ 
        this.respuestaAsignacion = respuesta;
      } );

      
      console.log(this.respuestaAsignacion);
      */
  }




}
