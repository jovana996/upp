import { Component, OnInit, ViewChild } from '@angular/core';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { TextService } from 'src/app/services/text.service';
import { FormFieldsComponent } from '../form-fields/form-fields.component';

@Component({
  selector: 'app-obrada-teksta',
  templateUrl: './obrada-teksta.component.html',
  styleUrls: ['./obrada-teksta.component.css']
})
export class ObradaTekstaComponent implements OnInit {

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

  constructor(private textService : TextService, private repositoryService : RepositoryService) {
    

   }

  ngOnInit() {
    console.log('hello')
     let x = this.textService.startProcess();
    x.subscribe(
      res => {
        console.log(res)
        this.formFieldsDto = res;
        this.form.setData(res);
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
