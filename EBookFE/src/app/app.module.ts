import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';

import { RepositoryService } from './services/repository/repository.service';
import { UserService } from './services/users/user.service';

import { RegistrationComponent } from './registration/registration.component';

import {Authorized} from './guard/authorized.guard';
import {Admin} from './guard/admin.guard';
import {Notauthorized} from './guard/notauthorized.guard';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { FormFieldsComponent } from './components/form-fields/form-fields.component';
import { NewMagazineComponent } from './components/new-magazine/new-magazine.component';
import { LoginComponent } from './components/login/login.component';
import { ObradaTekstaComponent } from './components/obrada-teksta/obrada-teksta.component';

const ChildRoutes =
  [
  ]

  const RepositoryChildRoutes =
  [
    
  ]

const Routes = [
  {
    path: "registrate",
    component: RegistrationComponent,
    canActivate: [Notauthorized]
  },
  {
    path: "activateAccount",
    component: ActivateAccountComponent,
    canActivate: [Authorized]
  },
  {
    path: "newMagazine",
    component: NewMagazineComponent,
    canActivate: [Authorized]
  },
  {
    path: "text",
    component: ObradaTekstaComponent,
    canActivate: [Authorized]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [Notauthorized]
  }
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    ActivateAccountComponent,
    FormFieldsComponent,
    NewMagazineComponent,
    LoginComponent,
    ObradaTekstaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(Routes),
    HttpClientModule, 
    HttpModule
  ],
  
  providers:  [
    Admin,
    Authorized,
    Notauthorized
    ],
    exports :[FormFieldsComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
