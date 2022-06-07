import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { DenunciaCiudadano } from '../modelos/DenunciaCiudadano';
import { ReclamoCiudadano } from '../modelos/ReclamoCiudadano';
import { ReclamoConductor } from '../modelos/ReclamoConductor';
import { WServRecDenService } from './WebServiceReclamosConsultas/wserv-rec-den.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalDenunciasComponent } from 'src/app/home/reclamos-consultas/modal-denuncias/modal-denuncias.component'

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
  //dataSource = ELEMENT_DATA_RECLAMO;
  //dataSourceDenuncia = ELEMENT_DATA_DENUNCIA;
  //dataSourceRConductor = ELEMENT_DATA_RECLAMO_CONDUCTOR;
  //displayedColumns: string[] = ['r_fechareclamo', 'r_ciudadano', 'r_reclamo', 'acciones'];
  //displayedColumnsRConductor: string[] = ['rc_fechareclamo', 'rc_conductor', 'rc_reclamo', 'acciones'];
  //displayedColumnsDemanda: string[] = ['d_fechadenuncia', 'd_tipodenuncia', 'd_descripciondenuncia', 'd_estado', 'd_nombrecompleto','acciones'];
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });

  displayedColumns: string[] = ['cFechaRecC', 'cNombreCompleto', 'cDescripcionRecC', 'acciones'];
  displayedColumnsRConductor: string[] = ['cFechaRecCo', 'cNombreCompleto', 'cDescripcionRecCo', 'acciones'];
  displayedColumnsDemanda: string[] = ['cFechaDenC', 'cModoDenC', 'cDescripcionDenC', 'lEstadoDenCo','acciones'];

  dataSource = new MatTableDataSource<ReclamoCiudadano>();
  dataSourceRConductor = new MatTableDataSource<ReclamoConductor>();
  dataSourceDenuncia = new MatTableDataSource<DenunciaCiudadano>();

  filtroNuevo_Visto_Todos:string;
  filtroAnonimo_Publico_Todos: string;

  filtroObtenidoNuevo_Visto_Todos:string;
  filtroObtenidoAnonimo_Publico_Todos: string;


  constructor(
    private WebServiceRD:WServRecDenService,
    private dialog: MatDialog,
    ) { }

  ngOnInit(): void {

    this.ActualizarTablaReclamosCiudadanos('2');
    this.ActualizarTablaReclamosConductores('2');
    this.ActualizarTablaDenunciasCiudadanos('2', '2');

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


  ActualizarTablaReclamosCiudadanos(filtroNVT:string) {

    this.filtroNuevo_Visto_Todos=filtroNVT;

    this.WebServiceRD.listarReclamosCiudadanos(
      {
        "filtroNuevo_Visto_Todos": this.filtroNuevo_Visto_Todos,
      }
    ).subscribe((RC: ReclamoCiudadano[])=>{
      this.dataSource = new MatTableDataSource();
      this.dataSource.data = RC;
    },
    error => {
      console.log('Se produjo un error mientras intentaba recuperar Lista de Reclamos de Ciudadanos!' + error);
    });
  }

  ActualizarTablaReclamosConductores(filtroNVT:string) {

    this.filtroNuevo_Visto_Todos=filtroNVT;

    this.WebServiceRD.listarReclamosConductores(
      {
        "filtroNuevo_Visto_Todos": this.filtroNuevo_Visto_Todos,
      }
    ).subscribe((RCo: ReclamoConductor[])=>{
      this.dataSourceRConductor = new MatTableDataSource();
      this.dataSourceRConductor.data = RCo;
    },
    error => {
      console.log('Se produjo un error mientras intentaba recuperar Lista de Reclamos de Conductores!' + error);
    });
  }

  ActualizarTablaDenunciasCiudadanos(filtroNVT:string, filtroAPT:string) {
    this.filtroNuevo_Visto_Todos=filtroNVT;
    this.filtroAnonimo_Publico_Todos= filtroAPT;

    this.WebServiceRD.listarDenunciasCiudadanos(
      {
        "filtroNuevo_Visto_Todos": this.filtroNuevo_Visto_Todos,
        "filtroAnonimo_Publico_Todos": this.filtroAnonimo_Publico_Todos,
      }
    ).subscribe((DC: DenunciaCiudadano[])=>{
      this.dataSourceDenuncia = new MatTableDataSource();
      this.dataSourceDenuncia.data = DC;
    },
    error => {
      console.log('Se produjo un error mientras intentaba recuperar Lista de Denuncias!' + error);
    });
  }









  clearForm(form: FormGroup, control: string) {
    form.get(control)?.setValue('');
  }
  clearrangeDate(form: FormGroup, inicio: string, fin: string) {
    form.get(inicio)?.setValue('');
    form.get(fin)?.setValue('');
  }





  public BusquedaReclamoCiudadano(){
    this.filtroObtenidoNuevo_Visto_Todos = this.searchForm.value.selectestado.toString();
    this.ActualizarTablaReclamosCiudadanos(this.filtroObtenidoNuevo_Visto_Todos);
  }
  public BusquedaReclamoConductor(){
    this.filtroObtenidoNuevo_Visto_Todos = this.searchFormRConduc.value.selectestadoC.toString();
    this.ActualizarTablaReclamosConductores(this.filtroObtenidoNuevo_Visto_Todos);
  }

  public BusquedaDenunciaCiudadano(){
    this.filtroObtenidoNuevo_Visto_Todos = this.searchFormDenuncia.value.selectEstadoD.toString();
    this.filtroObtenidoAnonimo_Publico_Todos = this.searchFormDenuncia.value.selectTipoD.toString();
    this.ActualizarTablaDenunciasCiudadanos(this.filtroObtenidoNuevo_Visto_Todos, this.filtroObtenidoAnonimo_Publico_Todos);

  }

  editarDenuncia(data: DenunciaCiudadano){
    const dialogRef = this.dialog.open(ModalDenunciasComponent, {
      width: '750px',
      disableClose: true,
      data: {
        data: data
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }





}
