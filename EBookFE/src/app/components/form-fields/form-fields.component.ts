import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/services/users/user.service';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { MagazineService } from 'src/app/services/magazine.service';
import { EnumValues } from 'src/app/enumValues';

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
  
  public enumValues = new Map<string,EnumValues>()
  public taskId ='';
  public userId;
  public magazineId;
  private magazinePreview = false;
  private createdMagazine;
  
  constructor(private userService: UserService, private repositoryService : RepositoryService, 
    private magazineService: MagazineService) {      
  this.magazinePreview = false;
  this.enumValues = new Map<string,EnumValues>();
  }

  ngOnInit() {
    this.formFields  = [];
    this.magazinePreview = false;
    this.enumValues = new Map<string,EnumValues>();
    console.log('this.formFieldsDto')

    console.log(this.formFieldsDto)


  }

  onSubmit(value, form){
    
    this.magazinePreview = false;
    let o = new Array();
    for (var property in value) {

      o.push({fieldId : property, fieldValue : value[property]});
    }
    console.log('call function ' + this.callFunction)
    if(this.callFunction == 'register'){
    let x = this.userService.registerUser(o, this.formFieldsDto.taskId);
    x.subscribe(
      res => {
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
      let x = this.userService.activateAccount(this.taskId, this.processInstance, this.userId);
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
      if(this.callFunction == 'approveReviewer'){

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
 
  if(this.callFunction == 'newMagazine'){

    let x = this.magazineService.newMagazine(o, this.formFieldsDto.taskId);

    x.subscribe(
      res => {
        alert("success!")
        this.magazineId = res;
        this.getNextTask();
      },
      err => {
        console.log(err);
        console.log("Error occured");
      }
    );
    }


    if(this.callFunction == 'scienceAreasMagazine'){
   
      let x = this.magazineService.enterScienceArea(o, this.formFieldsDto.taskId, this.magazineId);
  
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
      if(this.callFunction == 'addReviewer'){

        let x = this.magazineService.addReviewers(o, this.formFieldsDto.taskId, this.magazineId);
    
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

    if(this.callFunction == 'validateMagazine'){
   
      let x = this.magazineService.approveMagazine(this.magazineId, this.formFieldsDto.taskId, o);
  
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
      if(this.callFunction == 'chooseMagazine' ||
      this.callFunction == 'enterPaperData' ||
      this.callFunction == 'editorReviewingPaperData'||
      this.callFunction == 'editorReviewingPaper' ||
      this.callFunction == 'viewCommentsAndEdit' ||
      this.callFunction == 'choosePaperEditors' ||
      this.callFunction == 'analyzingReviews'||
      this.callFunction == 'editorDecision' ||
      this.callFunction == 'previewComments' ||
      this.callFunction == 'previewCommentsAndResponseAuthor' ||
      this.callFunction == 'saveReview'  || 
      this.callFunction ==  "previewCommentsAndResponseEditor"){
   
        let x = this.magazineService.chooseMagazine(this.formFieldsDto.taskId, o);
    
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
      this.setData(res)

        if(this.callFunction == 'validateMagazine'){
       
          let x = this.magazineService.validateMagazine(this.magazineId);
      
              x.subscribe(
                res => {
                  this.createdMagazine = res;
                  this.magazinePreview = true;
               
                },
                err => {
                  console.log(err);
                }
              );
          }

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

   setData(res){
    this.formFieldsDto = res;
    this.formFields = res.formFields;
    this.processInstance = res.processInstanceId;
    this.callFunction = res.taskDefinitionKey;
    this.taskId = res.taskId;
    this.formFields.forEach( (field) =>{
      console.log('field')
      console.log(field)
      if( field.type.name=='enum'){
      var enum1 = new EnumValues ;
      enum1.name = field.id;
      enum1.values =  Object.keys(field.type.values);
      this.enumValues.set(field.id, enum1);
      }
    });


   }

}
