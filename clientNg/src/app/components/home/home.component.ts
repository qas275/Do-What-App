import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { userAllDetails } from 'src/app/models';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  constructor(private svc: GeneralService, private router:Router){

  }
  
  email!: string;
  user!: userAllDetails;

  ngOnInit(): void {
    const email = sessionStorage.getItem('email');
    console.log(email)
    if(email){
      this.email = email;
      this.svc.svcUser.email = email;
      this.load(email)
      this.user = this.svc.svcUser
      console.log("init home with ",this.svc.svcUser)
    }
  }

  
  async load(email:string){
    let res = await this.svc.loadFavourties(email);
    console.log(res)
  }

  nav(idx:number){
    this.svc.selectedLocation = this.user.favorites[idx];
    this.router.navigate(['/details']);
  }
}
