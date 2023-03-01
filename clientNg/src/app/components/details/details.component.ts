import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TIHLocation } from 'src/app/models';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  constructor(private activatedRoute:ActivatedRoute, private svc:GeneralService, private router:Router, private fb:FormBuilder){
    
  }

  @ViewChild('picture') picture!:ElementRef

  selectedlocation!:TIHLocation
  commentForm!:FormGroup
  
  ngOnInit(): void {
    this.selectedlocation = this.svc.selectedLocation;
    this.selectedlocation.fav = false;
    this.svc.svcUser.favorites.forEach(loc =>{
      if(loc.uuid === this.selectedlocation.uuid){
        this.selectedlocation.fav =true;
      }
    })
    this.svc.getComments(this.selectedlocation.uuid).then(v=> {this.selectedlocation.locationComments= v});
    console.log("FAV?", this.selectedlocation.fav);
  }

  addFav(){
    console.log("SAVING")
    this.svc.svcUser.favorites.push(this.selectedlocation);
    this.svc.saveFavourite().then(
      v=>console.log(v)
    );
    alert("Location Saved!");
    this.router.navigate(['/home'])
  }
  
  removeFav(){
    const idxRemove = this.svc.svcUser.favorites.findIndex(v => v.uuid===this.selectedlocation.uuid);
    console.log(idxRemove)
    this.svc.svcUser.favorites.splice(idxRemove,1);
    this.svc.saveFavourite().then(
      v=>console.log("saving" +v)
    );
    alert('Removed from Favorites');
    this.router.navigate(['/home'])
  }

  addCommentForm(){
    this.commentForm = this.createForm();
  }

  createForm(){
    return this.fb.group({
      rating:this.fb.control(1, Validators.required),
      comment:this.fb.control('',Validators.required),
      picture:this.fb.control('', Validators.required) //check if required here works to disable button
    })
  }

  addComment(){
    //TODO
    const file = this.picture.nativeElement.files[0]
    this.svc.addComment(file, this.selectedlocation.uuid, 
      this.commentForm.controls['rating'].value, 
      this.commentForm.controls['comment'].value)
    
  }

  emailCheck(idx:number):boolean{
    console.log("EMAIL CHECK" + this.selectedlocation.locationComments[idx])
    return this.selectedlocation.locationComments[idx].email===sessionStorage.getItem("email")
  }

  deleteComment(post_id:string){
    this.svc.deleteComment(post_id).then(v=>{
      alert("removed comment!")
    });
    this.router.navigate(['/home'])
    // this.ngOnInit();
  }


}
