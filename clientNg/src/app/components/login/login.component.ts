import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GeneralService } from 'src/app/service/general.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm!: FormGroup;
  email = '';

  constructor(private svc:GeneralService, private formbuilder: FormBuilder, private router:Router){
    
  }

  ngOnInit(): void {
    this.loginForm = this.createLoginForm();  
  }

  createLoginForm(){
    return this.formbuilder.group({
      email: this.formbuilder.control(this.email, [Validators.required]),
      password: this.formbuilder.control('', [Validators.required])
    })
  }

  authenticate(){
    let login = 'false';
    this.email = this.loginForm.controls['email'].value;
    let password = this.loginForm.controls['password'].value;
    this.svc.checkLogin(this.email,password).then((v) =>{
      console.log(v); 
      login = v.login;
      if(login=='true'){
        console.log("Logging in with "+this.email+password);
        sessionStorage.setItem('email', this.email)
        this.router.navigate(['/home'])
      }else{
        alert("email: "+this.email+ " and password "+password+" does not exist or not match");
        this.ngOnInit();
      }
    });
  }

  async load(email:string){
    await this.svc.loadFavourties(email);
  }
}
