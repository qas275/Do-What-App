import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TIHLocation, TIHResponse } from 'src/app/models';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit{

  constructor(protected svc:GeneralService, private router:Router){

  }

  keyword = ""
  tihResp?:TIHResponse;

  ngOnInit(): void {
    this.keyword = this.svc.keyword
    this.tihResp = this.svc.response;
    this.svc.searchResults = this.tihResp?.data;
  }

  nav(idx:number){
    this.svc.selectedLocation = this.svc.searchResults[idx];
    this.router.navigate(['/details']);
  }
}
