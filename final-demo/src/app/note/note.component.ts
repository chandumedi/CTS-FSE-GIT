import { Component, Input } from '@angular/core';
import { RouterService } from '../services/router.service';
import { Note } from '../note';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material';
import { NotesService } from '../services/notes.service';
@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent {
  @Input() note: Note;
  constructor(private routerService: RouterService,
  private domSanitizer: DomSanitizer,
    public matIconRegistry: MatIconRegistry,private noteService:NotesService
  ){
    //add custom material icons
    matIconRegistry.addSvgIcon('delete_icon', domSanitizer.bypassSecurityTrustResourceUrl('../images/delete_icon.svg'));
}
  openEditView() {
    this.routerService.routeToEditNoteView(this.note.noteId);
   }
   deleteNote(noteId:number){
    
    this.noteService.deleteNote(noteId);
   }
}
