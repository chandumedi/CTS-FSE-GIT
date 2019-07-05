import { NgModule } from '@angular/core';
import { NotesService } from './services/notes.service';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { RouterService } from './services/router.service';
import { AuthenticationService } from './services/authentication.service';
import { CanActivateRouteGuard } from './can-activate-route.guard';

const appRoutes : Routes = [
  {path :'login', component:LoginComponent},
  {path :'dashboard', component:DashboardComponent,canActivate:[CanActivateRouteGuard]},
  {path :'',redirectTo:'login',pathMatch:'full'}
]


@NgModule({
  declarations: [HeaderComponent,
  AppComponent,
  LoginComponent,
DashboardComponent],
  imports: [
    BrowserModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatListModule,HttpClientModule,
    FormsModule,
    ReactiveFormsModule ,
    MatToolbarModule,
    RouterModule.forRoot(appRoutes) 
      
  ],
  providers: [NotesService,
    RouterService,
    AuthenticationService,
    CanActivateRouteGuard],
  bootstrap: [AppComponent]
})

export class AppModule { }
