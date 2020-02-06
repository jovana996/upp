import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( private userService : UserService) { }

  ngOnInit() {
  }
  onSubmit(value){

this.userService.login(value).subscribe(res => {
localStorage.setItem("user", JSON.stringify(res))
  console.log(res);
},err => {
  alert('Invalid username or password');
});
  
  }

}
