import { Component, Injectable } from '@angular/core';
import { FormControl, FormBuilder, Validators } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { FormGroupDirective } from '@angular/forms/';
import { FormGroup } from '@angular/forms';
import { LoginUser } from '../loginUser';
import { RouterService } from '../services/router.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
    
    public loginUser:LoginUser;
    public loginForm:FormGroup;
    username:FormControl;
    password:FormControl;
    submitMessage:string;
    
   
    @ViewChild(FormGroupDirective)
    formGroupDirective:FormGroupDirective;
    constructor(private routerService:RouterService, private authService:AuthenticationService,private formbuilder:FormBuilder) { 
    this.loginUser = new LoginUser();
      this.loginForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

    loginSubmit() {
      this.loginUser= this.loginForm.value;
      this.authService.authenticateUser(this.loginUser).subscribe(res =>{
        console.log(res['token']);
        this.authService.setBearerToken(res['token']);
        this.routerService.routeToDashboard();
      }),
      err=>{
        if(err.status=== 403){
        this.submitMessage= err.error.message;
        
      }else if(err.status=== 404){
        this.submitMessage= err.message;
        
      }
    }
      this.loginForm.reset();
      this.formGroupDirective.resetForm();
    }
    

}
