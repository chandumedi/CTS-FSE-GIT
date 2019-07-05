import { Category } from "./category";
import { Reminder } from "./reminder";
export class Note {
  noteId: Number;
  noteTitle: string;
  noteContent: string;
  noteStatus: string;
  category:Category;
  reminder:Array<Reminder>;
  constructor() {
    this.noteId=0;
    this.category=null;
    this.noteTitle = '';
    this.noteContent = '';
    this.noteStatus = 'not-started';
  }
}
