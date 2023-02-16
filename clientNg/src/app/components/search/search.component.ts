import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{
  constructor(private svc:GeneralService, private fb:FormBuilder, private router: Router){
    
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
    let response = await this.svc.search(this.searchForm.controls['keyword'].value)
    console.log({response})
    response.data.forEach(element => {
      console.log(element.name)
    });
    this.router.navigate(['/results'])
  }
}
