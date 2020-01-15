import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/users/user.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {

  constructor(private userService : UserService) { }

  ngOnInit() {
  }
  onSubmit(){
  
  }

}
