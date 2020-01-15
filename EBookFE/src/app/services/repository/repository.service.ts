import { Injectable } from '@angular/core';

import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient, private http : Http) { 
    

  }


  startProcess(){
    return this.httpClient.get('http://localhost:8080/register/get') as Observable<any>
  }

  getTask(taskId){
    return this.httpClient.get('http://localhost:8080/register/get/'.concat(taskId)) as Observable<any>
  }



}
