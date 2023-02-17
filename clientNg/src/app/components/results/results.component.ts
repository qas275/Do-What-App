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

  constructor(private svc:GeneralService, private router:Router){

  }

  tihResp?:TIHResponse;
  locations?:TIHLocation[];

  ngOnInit(): void {
      this.tihResp = this.svc.response;
      this.locations = this.tihResp?.data;
  }




}
