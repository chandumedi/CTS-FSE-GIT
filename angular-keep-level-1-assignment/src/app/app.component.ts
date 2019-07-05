import { Component, OnInit } from '@angular/core';
import { NotesService } from './notes.service';
import { Note } from './note';
import { promise } from 'selenium-webdriver';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  errMessage: string;
  notesList: Note[];
  noteObj:Note;
  constructor(private notesService: NotesService) {
    this.notesList = [];
    this.noteObj= new Note();
  }
  ngOnInit(){
    this.notesService.getNotes().subscribe(
      data => this.notesList = data,
      err => this.errMessage= err.message
    );
  }
   showCard(title,text){
     if(title === '' || text === ''){
      this.errMessage='Title and Text both are required fields';
      return null;
     }
    this.notesList.push(this.noteObj);
      this.notesService.addNote(this.noteObj).subscribe(
        data=> {this.errMessage = ''},
        
        err=>this.errMessage=err.message
      );
      this.noteObj=new Note();
  }
}
