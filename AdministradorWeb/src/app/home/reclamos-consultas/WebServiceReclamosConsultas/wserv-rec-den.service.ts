import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DenunciaCiudadano } from '../../modelos/DenunciaCiudadano';
import { ReclamoCiudadano } from '../../modelos/ReclamoCiudadano';
import { ReclamoConductor } from '../../modelos/ReclamoConductor';

@Injectable({
  providedIn: 'root'
})
export class WServRecDenService {

  constructor(private http:HttpClient) { }

 /* public post(url:string, body: any){
    return this.http.post(url, body);
  }
  */
 
  //url: string = 'http://192.168.1.60:8083/';
  url: string = 'http://localhost:7570/';

  public listarReclamosCiudadanos(body: any):Observable<ReclamoCiudadano[]>{
    return this.http.post<ReclamoCiudadano[]>(this.url + 'api/reclamociudadano/lista', body);
  }

  public listarReclamosConductores(body: any):Observable<ReclamoConductor[]>{
    return this.http.post<ReclamoConductor[]>(this.url + 'api/reclamoconductor/lista', body);
  }

  public listarDenunciasCiudadanos(body: any):Observable<DenunciaCiudadano[]>{
    return this.http.post<DenunciaCiudadano[]>(this.url + 'api/denunciaciudadano/listadenuncias', body);
  }


  public CambiarEstadoReclamoCiudadano( body: any){
    return this.http.post(this.url +'api/reclamociudadano/cambiar', body);
  }

  public CambiarEstadoReclamoConductor( body: any){
    return this.http.post(this.url +'api/reclamoconductor/cambiar', body);
  }

  public CambiarEstadoDenunciaCiudadano( body: any){
    return this.http.post(this.url +'api/denunciaciudadano/cambiar', body);
  }




}
