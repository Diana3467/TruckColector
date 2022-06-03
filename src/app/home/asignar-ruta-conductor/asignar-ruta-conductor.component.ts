import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { AsigRutaCond } from '../modelos/asig-ruta-cond';
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
  itemasig: AsigRutaCond[];

  respuestaAsignacion:boolean = false;

  fechaini_conver:string;
  fechafin_conver:string;


  displayedColumns: string[] = ['cInfoRuta', 'cInfoCond', 'acciones'];
  searchForm: any;
  dataSource = new MatTableDataSource<AsigRutaCond>();
  constructor(
    private http:HttpClient,
    private WebServiceRutaConductor: WservRutConService,
    
  ) { }

  ngOnInit(){

    this.ActualizarTablaRutaConductor();

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

  ActualizarTablaRutaConductor(){
    this.WebServiceRutaConductor.ListaRutaConductor().subscribe((item: AsigRutaCond[])=>{
      this.dataSource = new MatTableDataSource(); 
      this.dataSource.data = item;
    });
  }




  public AsignarRutaAlConductor(){
    //this.searchForm.value.selectruta.toString();
    //this.searchForm.value.selectconductor.toString();
    //this.searchForm.value.fecinit.toString();
    //this.searchForm.value.fecfin.toString();



    function convert(str: string) { 
      var date = new Date(str), 
      mnth = ("0" + (date.getMonth() + 1)).slice(-2), 
      day = ("0" + date.getDate()).slice(-2); 
      
      return [ date.getFullYear(), mnth, day].join("-"); 
    } 

    //console.log(convert(this.searchForm.value.fecinit.toString()))


    this.WebServiceRutaConductor.AsignarRutaConductor(
      {
        "nCodigoRuta": this.searchForm.value.selectruta.toString(),
        "nCodigoCond": this.searchForm.value.selectconductor.toString(),
        "dFechaInicio": convert(this.searchForm.value.fecinit.toString()),
        "dFechaFin": convert(this.searchForm.value.fecfin.toString()),
        "nCodigoAdm": '1',
      }
      ).subscribe(respuesta =>{ 
        this.respuestaAsignacion = respuesta;
        console.log(this.respuestaAsignacion);
        this.ActualizarTablaRutaConductor();
        if(this.respuestaAsignacion = true) this.EnviarNotif();
      } );
  }

  EnviarNotif(){


    function convert(str: string) { 
      var date = new Date(str), 
      mnth = ("0" + (date.getMonth() + 1)).slice(-2), 
      day = ("0" + date.getDate()).slice(-2); 
      
      return [day, mnth, date.getFullYear()].join("-"); 
    }

    this.WebServiceRutaConductor.EnviarNotificacion(
      {
        "to":"cYdsEqaTRqm1iNa2DWOy_o:APA91bGSlyVHDOa49-hbxezoMn9UdmnY0GE_lWyVHZxAJBCB_YXvyT9n_uJMEY0m8lYzzseSEcsBE5g65aXnoPlbj9smy4QFyogwd9SBbkljYJc81IYW7JuT1_xfJjEy3J_bUQfYBgw2",
        "notification": {
            "title": "ASIGNACIÓN DE RUTA",
            "body": "Buen día señor conductor, a usted se le asignó la ruta " + this.searchForm.value.selectruta.toString() +". Desde la fecha   "+ 
            convert(this.searchForm.value.fecinit.toString())+ " hasta la fecha  "+ convert(this.searchForm.value.fecfin.toString())+". ",
        }
      }
      ).subscribe(respuesta =>{ 
      } );
  }


}
