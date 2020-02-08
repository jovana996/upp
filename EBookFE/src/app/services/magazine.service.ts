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
  enterScienceArea(scienceArea, taskId, magazineId) {
    return this.httpClient.post("http://localhost:8080/magazine/scienceArea/".concat(taskId).concat('/').concat(magazineId), scienceArea) as Observable<any>;
  }
  addReviewers(scienceArea, taskId, magazineId) {
    return this.httpClient.post("http://localhost:8080/magazine/addReviewers/".concat(taskId).concat('/').concat(magazineId), scienceArea) as Observable<any>;
  }
  validateMagazine(magazineId){

    return this.httpClient.get("http://localhost:8080/magazine/validateMagazine/".concat(magazineId)) as Observable<any>;

  }
  approveMagazine(magazineId, taskId, approveMagazine){

    return this.httpClient.post("http://localhost:8080/magazine/approveMagazine/".concat(taskId).concat('/').concat(magazineId), approveMagazine) as Observable<any>;

  }
  chooseMagazine(taskId, magazine){
    return this.httpClient.post("http://localhost:8080/magazine/chooseMagazine/".concat(taskId), magazine) as Observable<any>;

  }
}
