import { Component, OnInit } from '@angular/core';
import { Note } from '../note';
import { NotesService } from '../services/notes.service';
import { RouterService } from '../services/router.service';
import { CategoryService } from '../services/category.service';
import { Category } from '../category';
import { Reminder } from '../reminder';
import { ReminderService } from '../services/reminder.service';
@Component({
  selector: 'app-note-taker',
  templateUrl: './note-taker.component.html',
  styleUrls: ['./note-taker.component.css']
})
export class NoteTakerComponent implements OnInit  {
  errMessage: String;
  categoryid:string;
  note: Note = new Note();
  notes: Array<Note> = [];
  categories: Array<Category> ;
  reminders:Array<Reminder>;
  constructor(private noteService: NotesService,private routerService: RouterService,private categoryService:CategoryService,private reminderService:ReminderService) { }
  ngOnInit() {

    this.reminderService.getReminders().subscribe(
      reminders=>{
        console.log(reminders);
        this.reminders=reminders;
      },
      err => this.errMessage = err.message
    );

    this.categoryService.getCategories().subscribe(
      categories=>{
        this.categories=categories
      },
      err => this.errMessage = err.message
    );

    this.noteService.getNotes().subscribe(
      notes => this.notes = notes,
      err => this.errMessage = err.message
    );
  }
  
  takeNotes() {
    if (this.note.noteTitle && this.note.noteContent) {
      const category=this.categoryService.getCategoryById(this.categoryid);
      category.id=this.categoryid;
      this.note.category=category;
      this.noteService.addNote(this.note).subscribe(
        res => {
          //this.errMessage=res.noteTitle+' is created';
          this.note=new Note();
          this.routerService.routeToNoteView();
        },
        err => {
          this.errMessage = err.message;
        }
      );
      this.note = new Note();
    } else {
        this.errMessage = 'Title and Text both are required fields';
    }
  }
  takecategory(){
    this.routerService.routeToOpenCategory();
  }

  showCategories(){
    this.routerService.routeToCategoryList();
  }
}
