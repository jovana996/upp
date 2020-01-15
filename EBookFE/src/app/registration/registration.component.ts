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
    
  /*  let x = repositoryService.startProcess();
    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
      },
      err => {
        console.log("Error occured");
      }
    );*/
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
  /*onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.userService.registerUser(o, this.formFieldsDto.taskId);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("You registered successfully!")
        this.getNextTask();
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  getTasks(){
    let x = this.repositoryService.getTasks(this.processInstance);

    x.subscribe(
      res => {
        console.log(res);
        this.tasks = res;
      },
      err => {
        console.log("Error occured");
      }
    );
   }

   getNextTask(){
    let x = this.repositoryService.getTask(this.processInstance);

  
    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        console.log("Error occured");
      }
    );
   }

   complete(taskId){
    let x = this.repositoryService.completeTask(taskId);

    x.subscribe(
      res => {
        console.log(res);
        this.tasks = res;
      },
      err => {
        console.log("Error occured");
      }
    );
   }
*/
}
