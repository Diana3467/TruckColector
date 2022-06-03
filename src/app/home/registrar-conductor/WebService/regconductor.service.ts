import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Conductor } from '../../modelos/conductor';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegconductorService {

  constructor(private http:HttpClient) { }

  //url: string = 'http://192.168.1.60:8083/';
  url: string = 'http://localhost:7570/';

  public post( body: any){
    let header_reqs = this.createRequestOptions();
    return this.http.post(this.url +'api/conductor/', body, { headers: header_reqs });
  }

  private createRequestOptions() {
    let headers = new HttpHeaders({
        "Content-Type": "application/json"
    });
    return headers;
  }

  public listarconductores():Observable<Conductor[]>{
    return this.http.get<Conductor[]>(this.url + 'api/conductor/listaconductor');
  }

}
