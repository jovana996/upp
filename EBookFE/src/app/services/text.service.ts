import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor(private httpClient: HttpClient) { }

  startProcess(){
    return this.httpClient.get('http://localhost:8080/text/get') as Observable<any>
  }
}
