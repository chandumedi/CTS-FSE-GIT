import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { RouterService } from './services/router.service';
import { AuthenticationService } from './services/authentication.service';


@Injectable()
export class CanActivateRouteGuard implements CanActivate {

  public bearerToken:string;
  public isAuthenticated:boolean;

  constructor(private routerService:RouterService ,private authService:AuthenticationService)
  {
    this.bearerToken = this.authService.getBearerToken();
  }


  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot){
      const isAuth=this.authService.isUserAuthenticated(this.bearerToken).
      then((data)=>{
        return data;
      });
      if(isAuth){
        return true;
      }else{
        return false;
      }
    }
}
