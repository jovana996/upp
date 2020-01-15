import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/services/users/user.service';
import { RepositoryService } from 'src/app/services/repository/repository.service';

@Component({
  selector: 'app-form-fields',
  templateUrl: './form-fields.component.html',
  styleUrls: ['./form-fields.component.css']
})
export class FormFieldsComponent implements OnInit {

  @Input() 
  formFieldsDto : any;
  @Input() 
  callFunction : string;

  public formFields = [];
  public processInstance = "";
  public enumValues = [];
  public taskId ='';
public userId;

  constructor(private userService: UserService, private repositoryService : RepositoryService) {      
  
  }

  ngOnInit() {
    this.formFields  = [];
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {

      o.push({fieldId : property, fieldValue : value[property]});
    }
    console.log('call function ' + this.callFunction)
    if(this.callFunction == 'register'){
    let x = this.userService.registerUser(o, this.formFieldsDto.taskId);
    x.subscribe(
      res => {
        console.log('userId? ' + res);
        this.userId = res;
        alert("You registered successfully!")
        this.getNextTask();
      },
      err => {
        console.log("Error occured");
      }
    );
    }
    if(this.callFunction == 'activateAccount'){
      console.log('call activatee ', this.taskId)
      let x = this.userService.activateAccount(this.taskId, this.processInstance, this.userId);
      x.subscribe(
        res => {
          console.log('call ac' +res);
          alert("success!")
          this.getNextTask();
        },
        err => {
          console.log(err);
          console.log("Error occured");
        }
      );
      }
      if(this.callFunction == 'approveReviewer'){
        console.log('call approve reviewer ', this.taskId)
        console.log(o);
        let x = this.userService.approveReviewer(o, this.formFieldsDto.taskId, this.userId);

        x.subscribe(
          res => {
            alert("success!")
            this.getNextTask();
          },
          err => {
            console.log(err);
            console.log("Error occured");
          }
        );
        }
        if(this.callFunction == 'enterScienceArea'){
          console.log('call enterScienceArea ', this.taskId)
          console.log(o);
          let x = this.userService.enterScienceArea(o, this.formFieldsDto.taskId, this.userId);
  
          x.subscribe(
            res => {
              alert("success!")
              this.getNextTask();
            },
            err => {
              console.log(err);
              console.log("Error occured");
            }
          );
          }
  
  }
  getNextTask(){
    let x = this.repositoryService.getTask(this.processInstance);

  
    x.subscribe(
      res => {
        if(res){
        console.log(res)
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.callFunction = res.taskDefinitionKey;
        this.taskId = res.taskId;
        this.formFields.forEach( (field) =>{
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      }else{
        alert("Process finished")
        
      }
    }
      ,
      err => {
        console.log("Error occured");
      }
    );
   }

}
