import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TIHLocation, TIHResponse } from 'src/app/models';
import { DataService } from 'src/app/service/data.service';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit{

  constructor(protected dataSvc:DataService ,private svc:GeneralService, private router:Router){

  }

  keyword = ""
  tihResp?:TIHResponse;

  ngOnInit(): void {
    this.keyword = this.dataSvc.keyword
    this.tihResp = this.dataSvc.response;
    this.dataSvc.searchResults = this.tihResp?.data;
  }

  nav(idx:number){
    this.dataSvc.selectedLocation = this.dataSvc.searchResults[idx];
    this.router.navigate(['/details']);
  }
}
