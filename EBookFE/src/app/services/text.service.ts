import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor(private httpClient: HttpClient) { }

  startProcess(user){
    return this.httpClient.post('http://localhost:8080/text/get', user) as Observable<any>
  }
}
