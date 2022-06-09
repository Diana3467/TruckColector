import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Carro } from '../../modelos/carro';
import { Conductor } from '../../modelos/conductor';
import { Utils } from '../../util/utils';

@Injectable({
  providedIn: 'root'
})
export class WservConductorCarroService {

  constructor(private http:HttpClient) { }

  url:string  = Utils.url;

  public getlistaconductores(){
    return this.http.get<Conductor[]>(this.url + 'api/conductor/listaconductor');
  }
  public getlistacarros(){
    return this.http.get<Carro[]>(this.url + 'api/carro/listacarro');
  }

}
