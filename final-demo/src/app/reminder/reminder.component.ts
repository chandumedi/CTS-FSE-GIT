import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import {ReminderService} from '../services/reminder.service';
import { RouterService } from '../services/router.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import { Category } from '../category';
import { Reminder } from '../reminder';

@Component({
  selector: 'app-reminder',
  templateUrl: './reminder.component.html',
  styleUrls: ['./reminder.component.css']
})
export class ReminderComponent implements OnInit {
  submitMessage:string;
  reminders:Array<Reminder>;
    reminderTypes:Array<string>=["Critical","Medium","Warning"];
    reminderName = new FormControl('', [Validators.required]);
    reminderDescription = new FormControl('');
    reminderType = new FormControl('');
    constructor(private reminderService:ReminderService,private routerService:RouterService,
      private authService:AuthenticationService,private httpClient:HttpClient) {
      
    }

  ngOnInit() {
    this.reminderService.getReminders().subscribe(
      reminders=>{
        console.log(reminders);
        this.reminders=reminders;
      },
      err => this.submitMessage = err.message
    );
  }
  
  addReminder(){
  const reminder: any={reminderName:this.reminderName.value,reminderDescription:this.reminderDescription.value,reminderType:this.reminderType.value}
    if (this.reminderName.hasError('required') || this.reminderDescription.hasError('required')||this.reminderType.hasError('required')) {
      this.submitMessage = 'Reminder name  and Description are required';
    }else{
        this.reminderService.addReminder(reminder).subscribe(
          response=>{
            this.submitMessage =response.reminderName+' added succesfully';
            this.routerService.routeToReminder();
          },err=>{
            if(err.status==201){
              console.log(err);
              this.routerService.routeToReminder();
            }
            
          }
        )
    }
  }
}
