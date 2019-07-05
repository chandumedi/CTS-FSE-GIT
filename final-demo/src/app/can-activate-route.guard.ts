import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { RouterService } from './services/router.service';
import { AuthenticationService } from './services/authentication.service';
@Injectable()
export class CanActivateRouteGuard implements CanActivate {
  constructor(private authService: AuthenticationService,
    private routeService: RouterService) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      const booleanPromise = this.authService.isUserAuthenticated(this.authService.getBearerToken());
      
        if (!booleanPromise) {
          this.routeService.routeToLogin();
        }
        return booleanPromise;
    
  }
}
