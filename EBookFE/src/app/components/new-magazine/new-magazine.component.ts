import { Component, OnInit, ViewChild } from '@angular/core';
import { FormFieldsComponent } from '../form-fields/form-fields.component';
import { MagazineService } from 'src/app/services/magazine.service';
import { RepositoryService } from 'src/app/services/repository/repository.service';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.css']
})
export class NewMagazineComponent implements OnInit {


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

  constructor(private magazineService : MagazineService, private repositoryService : RepositoryService) {
    this.formFieldsDto = [];
    this.enumValues = [];
    console.log('hellooo');
   }

  ngOnInit() {
    console.log('hellooo');
    this.formFieldsDto = [];
    this.enumValues = [];
    let x = this.magazineService.startProcess();

    x.subscribe(
      res => {
        this.formFieldsDto = res;
        this.loadData();
        console.log("after iniy this.formFieldsDto")
        console.log(this.formFieldsDto)
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
