import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Note } from '../note';
import { AuthenticationService } from './authentication.service';
import 'rxjs/add/operator/do';
@Injectable()
export class NotesService { 

  notes: Array<Note>;
  notesSubject: BehaviorSubject<Array<Note>>;
  constructor(private http: HttpClient,
    private authService: AuthenticationService) {
    this.notes = [];
    this.notesSubject = new BehaviorSubject(this.notes);
  }
  fetchNotesFromServer() {
    let header = new HttpHeaders({
      'Authorization':`Bearer ${this.authService.getBearerToken()}`,
      'Access-Control-Allow-Origin':"*",
      'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
      'Access-Control-Allow-Credentials':"true"
    });
    return this.http.get<Array<Note>>('http://localhost:8089/api/v1/note/'+`${this.authService.getUserId()}`, {
      headers: header
    }).subscribe(notes => {
      this.notes = notes;
      this.notesSubject.next(this.notes);
    }, (err) => {});
  }
  getNotes(): BehaviorSubject<Array<Note>> {
    return this.notesSubject;
  }
  addNote(note: Note): Observable<Note> {
    return this.http.post<Note>('http://localhost:8089/api/v1/note', note, {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    }).do(addedNote => {
       if(this.notes!=null){
        this.notes.push(addedNote);
       }
       this.notesSubject.next(this.notes);
    });
  }
  editNote(note: Note): Observable<Note> {
    return this.http.put<Note>('http://localhost:8089/api/v1/note/'+this.authService.getUserId()+'/'+note.noteId, note, {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    }).do(addedNote => {
      const selectedNote = this.notes.find((currentNote) => currentNote.noteId === addedNote.noteId);
      Object.assign(selectedNote, addedNote);
      this.notesSubject.next(this.notes);
    });
  }
  getNoteById(noteId): Note {
    const selectedNote = this.notes.find(note => note.noteId === noteId);
    return Object.assign({}, selectedNote);
  }

  deleteNote(noteId:number){
    return this.http.delete('http://localhost:8089/api/v1/note/'+this.authService.getUserId()+'/'+noteId).subscribe(res=>{
        
    },
    error=>{
      const selectedCategory = this.notes.find((currentCategory) => currentCategory.noteId === noteId);
        const index: number = this.notes.indexOf(selectedCategory);
        console.log(index);
        if (index !== -1) {
          this.notes.splice(index, 1);
      } 
        this.notesSubject.next(this.notes);
    });
  }
}
