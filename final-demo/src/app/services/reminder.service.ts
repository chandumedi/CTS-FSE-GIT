import { Injectable } from '@angular/core';
import { Reminder } from '../reminder';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReminderService {

  reminders:Array<Reminder>;
  reminderSubject: BehaviorSubject<Array<Reminder>>;
    constructor(private httpClient: HttpClient,private authService:AuthenticationService) {
      this.reminders=[];
      this.reminderSubject=new BehaviorSubject(this.reminders);
      }
    
      fetchReminderFromServer() {
        let header = new HttpHeaders({
          'Authorization':`Bearer ${this.authService.getBearerToken()}`,
          'Access-Control-Allow-Origin':"*",
          'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
          'Access-Control-Allow-Credentials':"true"
        });
        return this.httpClient.get<Array<Reminder>>('http://localhost:8085/api/v1/reminder/', {
          headers: header
        }).subscribe(categories => {
          this.reminders = categories;
          this.reminderSubject.next(this.reminders);
        }, (err) => {});
      }
      getReminders():BehaviorSubject<Array<Reminder>>{
        return this.reminderSubject;
      }
      addReminder(data:Reminder): Observable<Reminder> {
        let header = new HttpHeaders({
          'Authorization':`Bearer ${this.authService.getBearerToken()}`,
          'Access-Control-Allow-Origin':"*",
          'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
          'Access-Control-Allow-Credentials':"true"
        });
        console.log(header.get('Authorization'));
        return this.httpClient.post<Reminder>('http://localhost:8085/api/v1/reminder', data ,
         {headers:header}
        ).do(addedeCategory=>{
          this.reminders.push(addedeCategory);
          this.reminderSubject.next(this.reminders);
        });
      }
    
}
