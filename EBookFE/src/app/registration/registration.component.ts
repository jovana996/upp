import { Component, OnInit, ViewChild } from '@angular/core';
import {UserService} from '../services/users/user.service';
import {RepositoryService} from '../services/repository/repository.service';
import { FormFieldsComponent } from '../components/form-fields/form-fields.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  
  @ViewChild("form")
  form: FormFieldsComponent;
  
  private repeated_password = "";
  private categories = [];
  private formFieldsDto;
  private formFields = [];
  private choosen_category = -1;
  private processInstance = "";
  private enumValues = [];
  private tasks = [];

  constructor(private userService : UserService, private repositoryService : RepositoryService) {
    
   }

  ngOnInit() {
     let x = this.repositoryService.startProcess();
    x.subscribe(
      res => {
        this.formFieldsDto = res;
        this.loadData();
      },
      err => {
        console.log("Error occured");
      }
    );
   }
  
loadData(){
  this.form.formFields = this.formFieldsDto.formFields;
  this.form.processInstance =  this.formFieldsDto.processInstanceId;
  this.form.callFunction = this.formFieldsDto.taskDefinitionKey;
  this.form.formFields.forEach( (field) =>{
        if( field.type.name=='enum'){
          this.enumValues = Object.keys(field.type.values);
        }
      })
}
  
}
