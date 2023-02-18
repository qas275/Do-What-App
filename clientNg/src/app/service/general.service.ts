import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, lastValueFrom } from 'rxjs'
import { TIHLocation, TIHResponse, userAllDetails } from '../models';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  constructor( private http:HttpClient) { }

  response!: TIHResponse;
  selectedIdx = 0;
  svcUser:userAllDetails = {email:'', numFavs:0,favorites:[]};

  async checkLogin(email:string, password: string){
    let params =  new HttpParams().set('email', email).set('password', password);
    let response = await lastValueFrom(this.http.get('http://localhost:8080/login',{params: params})).then(
      (res) =>{
        console.log(res)
        return res;
      }
    );
    this.svcUser.email = email;
    console.log(response);
    return JSON.parse(JSON.stringify(response));
  }

  async register(email:string, password: string){
    let headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded')
    let params =  new HttpParams().set('email', email).set('password', password);
    let response = await lastValueFrom(this.http.post('http://localhost:8080/register',params.toString(), {headers:headers})).then(
      (res) =>{
        console.log(res)
        return res;
      }
    );
    console.log(response);
    return JSON.parse(JSON.stringify(response));
  }

  async loadFavourties(email:string){
    let params = new HttpParams().set('email', email);
    const jsonS:userAllDetails = await lastValueFrom<userAllDetails>(this.http.get<userAllDetails>('http://localhost:8080/load', { params: params }));
    console.warn(jsonS);
    this.svcUser.email = jsonS.email;
    this.svcUser.numFavs = jsonS.numFavs;
    if (this.svcUser.numFavs > 0) {
      this.svcUser.favorites = jsonS.favorites;  
    }
    return this.svcUser;

  }

  search(keyword:string){
    let params = new HttpParams().set('keyword', keyword);
    console.log("INIT SEARCH")
    return lastValueFrom(this.http.get<TIHResponse>('http://localhost:8080/search', {params:params}))
  }

  async saveFavourite(location:TIHLocation){
    // if(this.svcUser.favorites.filter(v=>{v.uuid==location.uuid}).length<1){
      this.svcUser.favorites.push(location);
    // }
    this.svcUser.email = sessionStorage.getItem('email')!
    this.svcUser.numFavs = this.svcUser.favorites.length;

    console.log("BEFORE SAVING WHAT IS USER??? >>> "+this.svcUser);
    console.log(this.svcUser);
    let response = await lastValueFrom(this.http.post('http://localhost:8080/addFav',this.svcUser)).then(
      (res) =>{
        console.log(res)
        return res;
      })
  }

  

}
