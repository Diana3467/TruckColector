import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Conductor } from '../../modelos/conductor';
import { Ruta } from '../../modelos/ruta';

@Injectable({
  providedIn: 'root'
})
export class WservRutConService {

  constructor(private http:HttpClient) { }

  public getlistaconductores(){
    return this.http.get<Conductor[]>('http://localhost:7570/api/conductor/listaconductor');
  }
  public getlistarutas(){
    return this.http.get<Ruta[]>('http://localhost:7570/api/ruta/listaruta');
  }

  private createRequestOptions() {
    let headers = new HttpHeaders({
        "Content-Type": "application/json"
    });
    return headers;
  }



}
