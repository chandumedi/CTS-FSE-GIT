import { Component, OnInit } from '@angular/core';
import { Note } from './../note';
import { NotesService } from './../services/notes.service';
import { MatTableDataSource } from '@angular/material';
import { CategoryService } from '../services/category.service';
import { Category } from '../category';
@Component({
  selector: 'app-note-view',
  templateUrl: './note-view.component.html',
  styleUrls: ['./note-view.component.css']
})
export class NoteViewComponent implements OnInit {
  notes: Array<Note> = [];
  errMessage: string;
  
  constructor(private noteService: NotesService) { }
  ngOnInit() {
    this.noteService.getNotes().subscribe(
      result => this.notes = result,
      err => {
        this.errMessage = err.error.message;
      }
    );
  }
}
