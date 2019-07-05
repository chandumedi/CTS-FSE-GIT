import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { User } from '../user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthenticationService {
  userId:string;
  message:string;
  constructor(private httpClient: HttpClient) {  }
  authenticateUser(data) {
    this.setUserId(data.userId);
    let header = new HttpHeaders();
    header.set('Access-Control-Allow-Origin', 'http://localhost:4200/');
    return this.httpClient.post('http://localhost:8081/api/v1/auth/login', data,{headers:header});
  }
  registerUser(data:User):Observable<string>{
    let header = new HttpHeaders();
    header.set('Access-Control-Allow-Origin', 'http://localhost:4200/');
    return this.httpClient.post<string>('http://localhost:8081/api/v1/auth/register',data,{headers:header}
  )
  }
  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }
  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }
  isUserAuthenticated(token): any{
    if(token!=""){
        return true;
    }
  }
  setUserId(userId){
    sessionStorage.setItem("userId",userId);
  }
  getUserId(){
    return sessionStorage.getItem("userId");
  }
}
