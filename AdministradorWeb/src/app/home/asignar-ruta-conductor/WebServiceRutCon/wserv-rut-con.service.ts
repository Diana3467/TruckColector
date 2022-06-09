import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Conductor } from '../../modelos/conductor';
import { Ruta } from '../../modelos/ruta';
import { AsigRutaCond } from '../../modelos/asig-ruta-cond';
import { Observable } from 'rxjs';
import { Utils } from '../../util/utils';

@Injectable({
  providedIn: 'root'
})
export class WservRutConService {

  constructor(private http:HttpClient) { }
  
  url:string  = Utils.url;

  public getlistaconductores(){
    return this.http.get<Conductor[]>(this.url + 'api/conductor/listaconductor');
  }
  public getlistarutas(){
    return this.http.get<Ruta[]>(this.url + 'api/ruta/listaruta');
  }

  public ListaRutaConductor(){
    return this.http.get<AsigRutaCond[]>(this.url + 'api/asigrutacond/lista');
  }
  


  public AsignarRutaConductor(body: any):Observable<boolean>{
    return this.http.post<boolean>(this.url + 'api/asigrutacond/asignar', body);
  }

  public EnviarNotificacion( body: any){
    let header_reqs = this.createRequestOptions();
    return this.http.post('https://fcm.googleapis.com/fcm/send', body, { headers: header_reqs });
  }

  private createRequestOptions() {
    let headers = new HttpHeaders({
        "Authorization": "key=AAAAdnGVr24:APA91bFveSbktI56ptxNgmY3URIXZaIBBmfbDOTTDaT0QQ4WtK-mY8R4d5tJsmLDmxwrWccha4oTdKtvL8KPjZDDWxxRjMAlOhLSyEi1gc3rzrUuhterbHUSYZtenLTS--V1S8XI-v8A",
        "Content-Type": "application/json"
    });
    return headers;
  }

}
