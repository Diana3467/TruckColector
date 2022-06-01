import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegconductorService {

  constructor(private http:HttpClient) { }

  public post(url:string, body: any){
    let header_reqs = this.createRequestOptions();
    return this.http.post(url, body, { headers: header_reqs });
  }

  private createRequestOptions() {
    let headers = new HttpHeaders({
        "Content-Type": "application/json"
    });
    return headers;
  }

}
