import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Carro } from '../../modelos/carro';
import { Conductor } from '../../modelos/conductor';

@Injectable({
  providedIn: 'root'
})
export class WservConductorCarroService {

  constructor(private http:HttpClient) { }

  //url: string = 'http://192.168.1.60:8083/';
  url: string = 'http://localhost:7570/';

  public getlistaconductores(){
    return this.http.get<Conductor[]>(this.url + 'api/conductor/listaconductor');
  }
  public getlistacarros(){
    return this.http.get<Carro[]>(this.url + 'api/carro/listacarro');
  }

}
