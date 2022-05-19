import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { ErrorStateMatcher } from '@angular/material/core';

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
  panelOpenState = false;
  cNombreCond = 'Clear me';
  searchForm: any;
  email = new FormControl('', [Validators.required, Validators.email]);
  displayedColumns: string[] = ['codigo', 'nombre', 'dni', 'edad', 'celular', 'direccion', 'correo', 'acciones'];
  dataSource = ELEMENT_DATA;

  
  
  constructor() {
  
   }

  ngOnInit(): void {
  }
  clearForm(form: FormGroup, control: string) {
    form.get(control)?.setValue('');
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
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

    


}
