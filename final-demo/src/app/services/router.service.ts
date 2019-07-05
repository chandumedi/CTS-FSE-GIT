import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
@Injectable()
export class RouterService {
  constructor (private router: Router,
               private location: Location) { }
  routeToDashboard() {
    this.router.navigate(['dashboard']);
  }
  routeToLogin() {
    this.router.navigate(['login']);
  }
  routeToEditNoteView(noteId) {
    this.router.navigate([
      'dashboard', {
        outlets : {
          noteEditOutlet : ['note', noteId, 'edit']
        }
      }
    ]);
  }
  routeToOpenCategory() {
    this.router.navigate([
      'dashboard', {
        outlets : {
          addCategoryOutlet : ['category']
        }
      }
    ]);
  }
  routeBack() {
    this.location.back();
  }
  routeToNoteView() {
    this.router.navigate(['dashboard/view/noteview']);
  }
  routeToCategoryList(){
    this.router.navigate(['dashboard/view/categorylist']);
  }
  routeToListView() {
    this.router.navigate(['dashboard/view/listview']);
  }
  routeToRegistratin() {
    this.router.navigate(['register']);
  }
  routeToReminder(){
    this.router.navigate(['reminder']);
  }
}
