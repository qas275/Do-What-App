import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, firstValueFrom, lastValueFrom } from 'rxjs'
import { LocationComment, TIHLocation, TIHResponse, userAllDetails } from '../models';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  constructor( private http:HttpClient) { }

  searchResults:TIHLocation[] = [];
  keyword = ""
  response!: TIHResponse;
  selectedLocation!: TIHLocation;
  selectedIdx = 0;
  svcUser:userAllDetails = {email:'', numFavs:0,favorites:[]};

  async checkLogin(email:string, password: string){
    let params =  new HttpParams().set('email', email).set('password', password);
    let response = await lastValueFrom(this.http.get('/login',{params: params})).then(
      (res) =>{
        console.log("service login result "+res)
        return res;
      }
    );
    this.svcUser.email = email;
    return JSON.parse(JSON.stringify(response));
  }

  async register(email:string, password: string){
    let headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded')
    let params =  new HttpParams().set('email', email).set('password', password);
    let response = await lastValueFrom(this.http.post('/register',params.toString(), {headers:headers})).then(
      (res) =>{
        console.log("service register response"+res)
        return res;
      }
    );
    return JSON.parse(JSON.stringify(response));
  }

  async loadFavourties(email:string){
    let params = new HttpParams().set('email', email);
    const jsonS:userAllDetails = await lastValueFrom<userAllDetails>(this.http.get<userAllDetails>('/load', { params: params }));
    this.svcUser.email = jsonS.email;
    this.svcUser.numFavs = jsonS.numFavs;
    if (this.svcUser.numFavs > 0) {
      this.svcUser.favorites = jsonS.favorites;  
    }
    return this.svcUser;

  }

  search(keyword:string){
    let params = new HttpParams().set('keyword', keyword);
    console.log("INIT SEARCH " +keyword)
    return lastValueFrom(this.http.get<TIHResponse>('/search', {params:params}))
  }

  async saveFavourite(){
    this.svcUser.numFavs = this.svcUser.favorites.length;
    console.log("BEFORE SAVING WHAT IS USER??? >>> "+this.svcUser.email);
    let response = await lastValueFrom(this.http.post('/updateFav',this.svcUser)).then(
      (res) =>{
        console.log("SAVED "+res)
        return res;
      })
  }

  addComment(image: Blob, location_id:string, rating: number, comment:string):Promise<any>{
    const formData = new FormData();
    formData.set("email", this.svcUser.email)
    formData.set("location_id", location_id)
    formData.set("rating", rating.toString())
    formData.set("image", image)
    formData.set("comment", comment)
    return firstValueFrom(
      this.http.post<any>('/comment', formData)
    )
  }

  getComments(UUID:String):Promise<LocationComment[]>{
    return lastValueFrom(
      this.http.get<LocationComment[]>('/getComments/'+UUID)
    )
  }

  deleteComment(idx:string){
    return lastValueFrom(
      this.http.get('/deleteComment/'+idx)
      )
    }
    
    deleteUser(email:string){
      let params = new HttpParams().set('email', email);
      return lastValueFrom(this.http.get('/deleteUser', {params:params}))
  }


  

}
