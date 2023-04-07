import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/data.service';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{
  constructor(private svc:GeneralService, private dataSvc: DataService, private fb:FormBuilder, private router: Router){
    
  }

  searchForm!:FormGroup

  ngOnInit(): void {
      this.searchForm = this.createSearchForm();
  }

  createSearchForm(){
    return this.fb.group({
      keyword: this.fb.control('', Validators.required)
    })
  }

  async search(){
    this.dataSvc.keyword = this.searchForm.controls['keyword'].value
    this.dataSvc.response = await this.svc.search(this.dataSvc.keyword)
    this.router.navigate(['/results'])
  }
}
