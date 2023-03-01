import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { ResultsComponent } from './components/results/results.component';
import { DetailsComponent } from './components/details/details.component';
import { CommentComponent } from './components/comment/comment.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';

const appRoutes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"", component:LoginComponent},
  {path:"search", component:SearchComponent},
  {path:"results", component:ResultsComponent},
  {path:"details", component:DetailsComponent},
  {path:"comment", component:CommentComponent},
  {path:"home", component:HomeComponent},
  {path:"register", component:RegisterComponent},
  {path:"profile", component:ProfileComponent},
  {path:"**", redirectTo:'/', pathMatch:'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SearchComponent,
    ResultsComponent,
    DetailsComponent,
    CommentComponent,
    HomeComponent,
    RegisterComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule, RouterModule.forRoot(appRoutes, {useHash:true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
