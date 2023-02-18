import { AfterViewInit, Component, OnInit } from '@angular/core';
import { userAllDetails } from 'src/app/models';
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
  user!: userAllDetails;

  ngOnInit(): void {
    const email = sessionStorage.getItem('email');
    console.log(email)
    if(email){
      this.email = email;
      // this.svc.svcUser.email = email;
      let res = this.load(email)
      
      console.log("in home",this.svc.svcUser)
      this.user = this.svc.svcUser
    }
  }

  
  async load(email:string){
    this.user = await this.svc.loadFavourties(email);
    console.log("asdasdad")
    return this.user.numFavs;
  }
}
