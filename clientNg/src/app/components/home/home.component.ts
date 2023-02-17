import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  constructor(private svc: GeneralService){

  }
  
  email!: string;

  ngOnInit(): void {
    const email = sessionStorage.getItem('email');
    if(email){
      this.email = email;
      this.svc.user.email = email;
    }
    
  }
}
