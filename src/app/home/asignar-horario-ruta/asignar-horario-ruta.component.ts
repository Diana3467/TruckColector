import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

export interface DatosTablaRutaConductor {
  codigo: number;
  ruta: string;
  frecuencia: string;
  horinit: string;
  horfin: string;
}
const ELEMENT_DATA: DatosTablaRutaConductor[] = [
  {codigo: 1, ruta:'Ruta 1: Umuto - Incho', frecuencia: 'Lunes, Miércoles y Viernes', horinit:'', horfin:''},
  {codigo: 2, ruta:'Ruta 8: Chacra Vieja', frecuencia: 'Lunes, Miércoles y Viernes', horinit:'', horfin:''},
  {codigo: 3, ruta:'Ruta 9: Batanyacu - Polideportivo', frecuencia: 'Martes, Jueves y Sábado', horinit:'', horfin:''},
];

@Component({
  selector: 'app-asignar-horario-ruta',
  templateUrl: './asignar-horario-ruta.component.html',
  styleUrls: ['./asignar-horario-ruta.component.css']
})
export class AsignarHorarioRutaComponent implements OnInit {
  searchForm: any;
  displayedColumns: string[] = ['codigo', 'ruta', 'frecuencia', 'horinit', 'horfin','acciones'];
  dataSource = ELEMENT_DATA;
  selectedrutaOption: string;
  selectedfrecuenciaOption: string;
  horainicio: string;
  horafin: string;
  constructor(
    private toastr: ToastrService,
  ) { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      selectruta: new FormControl(null),
      selectfrecuencia: new FormControl(null),
      horinit: new FormControl(null,[Validators.required]),
      horfin: new FormControl(null,[Validators.required]),
    });
  }
  clearForm(form: FormGroup, control: string) {
    form.get(control)?.setValue('');
  }
  asignar_horario_ruta(){
    var vruta = this.selectedrutaOption;
    var vfrecuencia = this.selectedfrecuenciaOption;
    var vhorinit = this.horainicio;
    var vhorfin = this.horafin;
    console.log(vruta);
    console.log(vfrecuencia);
    console.log(vhorinit);
    console.log(vhorfin);
    if(vruta=="ruta2"){

    }else{
      this.toastr.info(
        'false'
      );
    };

    return
  }

}
