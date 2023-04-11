import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { userAllDetails } from 'src/app/models';
import { AuthService } from 'src/app/service/auth.service';
import { DataService } from 'src/app/service/data.service';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  constructor(private svc: GeneralService, private dataSvc:DataService, private authSvc:AuthService, private router:Router){

  }
  
  email!: string;
  user!: userAllDetails;

  center: google.maps.LatLngLiteral = {
    lat: 22.2736308,
    lng: 70.7512555
  }

  ngOnInit(): void {
    const email = sessionStorage.getItem('email');
    console.log(email)
    if(email){
      this.email = email;
      this.dataSvc.svcUser.email = email;
      this.load(email)
      this.user = this.dataSvc.svcUser
      console.log("init home with ",this.dataSvc.svcUser)
    }
  }

  
  async load(email:string){
    let res = await this.svc.loadFavourties(email);
    console.log(res)
  }

  nav(idx:number){
    this.dataSvc.selectedLocation = this.user.favorites[idx];
    this.router.navigate(['/details']);
  }
  
  logout(){
    this.svc.logout();
    this.router.navigate(['/login']);
  }
}
