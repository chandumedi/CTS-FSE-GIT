import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';




@Injectable()
export class AuthenticationService {
  authUrl: string;
  constructor(private httpClient : HttpClient) {
    this.authUrl= 'http://localhost:3000/auth/v1';
  }

  authenticateUser(data) {
    return this.httpClient.post(this.authUrl, data);
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken',token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

   isUserAuthenticated(token) : Promise<boolean> {
    return new Promise((resolve,reject)=>{
      this.httpClient.post('https://localhost:3000/auth/v1/'+'isAuthenticated',{
        headers:{
          'Authorization': this.getBearerToken()
        }
      }).subscribe(res=> {
        resolve(res['isAuthenticated'])
      }),
      err=>{
        console.log(err);
      }
    })
  }
 }
