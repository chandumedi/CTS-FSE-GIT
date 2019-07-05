import { Component, OnInit } from '@angular/core';
import { NotesService } from '../services/notes.service';
import { Note } from '../note';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  notes: Note[];
  errMessage:string;
   noteObj:Note;
constructor(private notesService:NotesService){
  this.notes = [];
  this.noteObj=new Note();
}
  ngOnInit(){
    this.notesService.getNotes().subscribe(
      data => this.notes = data,
      err => this.errMessage= err.message
      
    );
  }
  addNote(title,text){
    if(title === '' || text === ''){
     this.errMessage='Title and Text both are required fields';
     return null;
    }
   this.notes.push(this.noteObj);
     this.notesService.addNote(this.noteObj).subscribe(
       data=> {this.errMessage = ''},
       
       err=>this.errMessage=err.message
     );
     this.noteObj=new Note();
 }
}
