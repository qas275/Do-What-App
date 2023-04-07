import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/service/data.service';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  constructor(private svc:GeneralService, protected dataSvc: DataService, public route:ActivatedRoute){

  }

  deleteUser(){
    let email = sessionStorage.getItem("email")!;
    this.svc.deleteUser(email);
    alert('Account Deleted')
  }
}
