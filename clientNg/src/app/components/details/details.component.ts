import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TIHLocation } from 'src/app/models';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  constructor(private activatedRoute:ActivatedRoute, private svc:GeneralService, private router:Router){
    
  }

  selectedlocation!:TIHLocation
  
  ngOnInit(): void {
    let idx:number = this.activatedRoute.snapshot.params['idx'];
    console.log(idx)
    this.selectedlocation = this.svc.response.data[idx];
    console.log(this.selectedlocation);
  }

  save(){
    console.log("SAVING")
    this.svc.saveFavourite(this.selectedlocation).then(
      v=>console.log(v)
    );
    alert("Location Saved!");
    this.router.navigate(['/home'])
  }


}
