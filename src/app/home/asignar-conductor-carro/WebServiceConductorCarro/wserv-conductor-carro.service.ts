import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Carro } from '../../modelos/carro';
import { Conductor } from '../../modelos/conductor';

@Injectable({
  providedIn: 'root'
})
export class WservConductorCarroService {

  constructor(private http:HttpClient) { }

  public getlistaconductores(){
    return this.http.get<Conductor[]>('http://localhost:7570/api/conductor/listaconductor');
  }
  public getlistacarros(){
    return this.http.get<Carro[]>('http://localhost:7570/api/carro/listacarro');
  }

}
