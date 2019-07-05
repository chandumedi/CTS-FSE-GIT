import { Component } from '@angular/core';
import { RouterService } from '../services/router.service';
import { AuthenticationService } from '../services/authentication.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isNoteView = true;
  loginUser:string;
  constructor(private routerService: RouterService,private userAuth:AuthenticationService) {
    this.loginUser=this.userAuth.getUserId();
   }
  switchToListView() {
    this.isNoteView = false;
    this.routerService.routeToListView();
  }
  switchToNoteView() {
    this.isNoteView = true;
    this.routerService.routeToNoteView();
  }
}
