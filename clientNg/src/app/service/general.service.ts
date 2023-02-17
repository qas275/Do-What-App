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
  user!:userAllDetails;

  async checkLogin(email:string, password: string){
    let params =  new HttpParams().set('email', email).set('password', password);
    let response = await lastValueFrom(this.http.get('http://localhost:8080/login',{params: params})).then(
      (res) =>{
        console.log(res)
        return res;
      }
    );
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

  loadFavourties(email:string){
    let params = new HttpParams().set('email', email);
    return lastValueFrom(this.http.get('http://localhost:8080/load', {params:params}))
  }

  search(keyword:string){
    let params = new HttpParams().set('keyword', keyword);
    console.log("INIT SEARCH")
    return lastValueFrom(this.http.get<TIHResponse>('http://localhost:8080/search', {params:params}))
  }

  async saveFavourite(location:TIHLocation){
    if(this.user.favorites.filter(v=>{v.uuid==location.uuid}).length<1){
      this.user.favorites.push(location);
    }
    let response = await lastValueFrom(this.http.post('http://localhost:8080/addFav',this.user)).then(
      (res) =>{
        console.log(res)
        return res;
      })
  }

  

}
