import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NoteComponent } from './note/note.component';
import { ListViewComponent } from './list-view/list-view.component';
import { NoteTakerComponent } from './note-taker/note-taker.component';
import { NoteViewComponent } from './note-view/note-view.component';
import { EditNoteOpenerComponent } from './edit-note-opener/edit-note-opener.component';
import { EditNoteViewComponent } from './edit-note-view/edit-note-view.component';
import { CanActivateRouteGuard } from './can-activate-route.guard';
import { AuthenticationService } from './services/authentication.service';
import { NotesService } from './services/notes.service';
import { RouterService } from './services/router.service';
import { RegistrationComponent } from './registration/registration.component';
import { CategoryComponent } from './category/category.component';
import { CategoryViewComponent } from './category-view/category-view.component';
import { CategoryService } from './services/category.service';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import { CategoryListComponent } from './category-list/category-list.component';
import {MatTabsModule} from '@angular/material/tabs';
import { ReminderComponent } from './reminder/reminder.component';
import { ReminderService } from './services/reminder.service';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'reminder',
    component: ReminderComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [CanActivateRouteGuard],
    children: [
      {
        path : '',
        redirectTo : 'view/noteview',
        pathMatch : 'full'
      },
      {
        path : 'view/noteview',
        component : NoteViewComponent
      },
      {
        path : 'view/listview',
        component : ListViewComponent
      },
      {
        path : 'note/:noteId/edit',
        component : EditNoteOpenerComponent,
        outlet : 'noteEditOutlet'
      },
      {
        path : 'category',
        component : CategoryViewComponent,
        outlet : 'addCategoryOutlet'
      },
      {
        path : 'view/categorylist',
        component : CategoryListComponent
      }
    ]
  }
];
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DashboardComponent,
    LoginComponent,
    NoteComponent,
    ListViewComponent,
    NoteTakerComponent,
    NoteViewComponent,
    EditNoteOpenerComponent,
    EditNoteViewComponent,
    RegistrationComponent,
    CategoryComponent,
    CategoryViewComponent,
    CategoryListComponent,
    ReminderComponent
  ],
  imports: [
    
    MatTabsModule,
    MatTableModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatInputModule,
    MatFormFieldModule,
    MatExpansionModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatSelectModule,
    MatIconModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AuthenticationService,
    NotesService,
    RouterService,
    CategoryService,
    CanActivateRouteGuard,
    ReminderService
  ],
  bootstrap: [AppComponent],
  entryComponents: [EditNoteViewComponent,CategoryComponent,CategoryViewComponent]
})
export class AppModule { }
