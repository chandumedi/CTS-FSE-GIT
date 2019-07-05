import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  userId = new FormControl('', [Validators.required]);
  userPassword = new FormControl('');
  firstName = new FormControl('');
  lastName = new FormControl('');
  userRole = new FormControl('');
  public bearerToken: any;
  public submitMessage: string;
  constructor(private authService: AuthenticationService,
    private routerService: RouterService) {
    }

  ngOnInit() {
  }

  registerUser(){
    const user: any={userId:this.userId.value,userPassword:this.userPassword.value,firstName:this.firstName.value,
    lastName:this.lastName.value,userRole:this.userRole.value}
    if (this.userId.hasError('required') || this.userPassword.hasError('required')||this.firstName.hasError('required')) {
      this.submitMessage = 'Username and Password and first name required';
    }else{
        this.authService.registerUser(user).subscribe(
          response=>{
            this.submitMessage =response;
            this.routerService.routeToLogin();
          },err=>{
            if(err.status==201){
              console.log(err);
              this.routerService.routeToLogin();
            }else{
              this.submitMessage = err.error;
            }
            
          }
        )
    }
    
  }
}
