import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  constructor(private httpClient: HttpClient) { }

  
  startProcess(){
    return this.httpClient.get('http://localhost:8080/magazine/get') as Observable<any>
  }

  newMagazine(magazine, taskId) {
    return this.httpClient.post("http://localhost:8080/magazine/post/".concat(taskId), magazine) as Observable<any>;
  }
}
