import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { ErrorStateMatcher } from '@angular/material/core';
import { RegconductorService } from 'src/app/regconductor.service';

export interface DatosTablaRegistrarConductor {
  codigo: number;
  nombre: string;
  dni: number;
  edad: number;
  celular: number;
  direccion: string;
  correo: string;
}

const ELEMENT_DATA: DatosTablaRegistrarConductor[] = [
  {codigo: 1, nombre: 'Karina Perez', dni: 74264528, edad: 24, celular: 964284635, direccion: '', correo: 'kperez@gmail.com'},
  {codigo: 2, nombre: 'Maria Torres', dni: 74297528, edad: 32, celular: 964752735, direccion: '', correo: 'mtorres@gmail.com'},
  {codigo: 3, nombre: 'Jose Suarez', dni: 72164598, edad: 28, celular: 911224635, direccion: '', correo: 'jsuarez@gmail.com'},
];

@Component({
  selector: 'app-registrar-conductor',
  templateUrl: './registrar-conductor.component.html',
  styleUrls: ['./registrar-conductor.component.css']
})



export class RegistrarConductorComponent implements OnInit {
  panelFilterOpenState = false;
  panelOpenState = false;
  cNombreCond = 'Clear me';
  searchForm: any;
  displayedColumns: string[] = ['codigo', 'nombre', 'dni', 'edad', 'celular', 'direccion', 'correo', 'acciones'];
  dataSource = ELEMENT_DATA;

  

  constructor(private RegConductor:RegconductorService, private formBuilder:FormBuilder) {
    
   }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      nombre: new FormControl(null,[Validators.required, Validators.pattern(/^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/)]),
      apepaterno: new FormControl(null,[Validators.required, Validators.pattern(/^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/)]),
      apematerno: new FormControl(null,[Validators.required, Validators.pattern(/^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/)]),
      dni: new FormControl(null,[Validators.required, Validators.minLength(8)]),
      edad: new FormControl(null,[Validators.required]),
      celular: new FormControl(null,[Validators.required, Validators.minLength(9)]),
      direccion: new FormControl(null),
      correo: new FormControl(null),
    });
  }

  clearForm(form: FormGroup, control: string) {
    form.get(control)?.setValue('');
  }

  validateFormat(event:any) {
    let key;
    if (event.type === 'paste') {
      key = event.clipboardData.getData('text/plain');
    } else {
      key = event.keyCode;
      key = String.fromCharCode(key);
    }
    const regex = /[0-9]|\./;
     if (!regex.test(key)) {
      event.returnValue = false;
       if (event.preventDefault) {
        event.preventDefault();
       }
     }
    }



    public enviarData(){
      this.RegConductor.post(`http://localhost:7570/api/conductor/`,
      {
        "cNombreCond": this.searchForm.value.nombre.toString(),
        "cApePatCond": this.searchForm.value.apepaterno.toString(),
        "cApeMatCond": this.searchForm.value.apematerno.toString(),
        "cDNICond": this.searchForm.value.dni.toString(),
        "cEdadCond": this.searchForm.value.edad.toString(),
        "cCelCond": this.searchForm.value.celular.toString(),
        "cDireccCond": this.searchForm.value.direccion.toString(),
        "cCorEleCond": this.searchForm.value.correo.toString()
      }
      ).subscribe(respuesta =>{
        console.log('Comentario enviado!!');
      } )

    }

    registrarconductor(){
      console.log("Entro")
    }

}
