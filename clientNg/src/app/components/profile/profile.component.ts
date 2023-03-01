import { Component } from '@angular/core';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  constructor(private svc:GeneralService){

  }

  deleteUser(){
    let email = sessionStorage.getItem("email")!;
    this.svc.deleteUser(email);
  }
}
