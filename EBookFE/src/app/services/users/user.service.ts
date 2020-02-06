import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  fetchUsers() {
    return this.httpClient.get("http://localhost:8080/user/fetch") as Observable<any>;
  }

  registerUser(user, taskId) {
    return this.httpClient.post("http://localhost:8080/register/post/".concat(taskId), user) as Observable<any>;
  }

  activateAccount(taskId, processInstanceId, userId){
    return this.httpClient.get("http://localhost:8080/register/activateAccount/".concat(taskId).concat("/").concat("/").concat(userId)) as Observable<any>;

  }
  approveReviewer(approve, taskId, userId) {
    return this.httpClient.post("http://localhost:8080/register/approveReviewer/".concat(taskId).concat("/").concat(userId), approve) as Observable<any>;
  }
  enterScienceArea(scienceArea, taskId, userId) {
    return this.httpClient.post("http://localhost:8080/register/scienceArea/".concat(taskId).concat("/").concat(userId), scienceArea) as Observable<any>;
  }
  login(form) {
    return this.httpClient.post("http://localhost:8080/auth/login/", form) as Observable<any>;
  }
}
