import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {PaymentType} from "../model/students.model";
import {StudentsService} from "../services/students.service";

@Component({
  selector: 'app-new-payment',
  templateUrl: './new-payment.component.html',
  styleUrl: './new-payment.component.css'
})
export class NewPaymentComponent implements OnInit{
paymentFormGroup !:FormGroup;
studentCode! :string;
paymentTypes :string[]=[];
pdfFileUrl! : string;
showProgress :boolean=false;

constructor(private fb:FormBuilder,private  activatedRoute :ActivatedRoute,private studentsService :StudentsService) {
}
ngOnInit() {
  for(let element in PaymentType)
  {
    let value=PaymentType[element];
    if(typeof value==='string')

    { this.paymentTypes.push(PaymentType[element].valueOf());}
  }
  this.studentCode=this.activatedRoute.snapshot.params['code'];
  this.paymentFormGroup=this.fb.group({
    date:this.fb.control(''),
    amount:this.fb.control(''),
    type:this.fb.control(''),
    studentCode:this.fb.control(this.studentCode),
    fileSource :this.fb.control(''),
    fileName:this.fb.control(''),
  })
}


  selectFile(event: any) {

  //si il y a au moins un fichier
    if(event.target.files.length>0)

      {//upload ce fichier
let file=event.target.files[0];
this.paymentFormGroup.patchValue({
  fileSource:file,
  fileName:file.name
});
this.pdfFileUrl=window.URL.createObjectURL(file);
    }
  }

  savePayment() {
  this.showProgress=true;
  let date =new Date(this.paymentFormGroup.value.date);
  console.log(this.paymentFormGroup.value.date)
    console.log(date.getDate());
    console.log(date.getMonth()+1);
    console.log(date.getFullYear());
let date_=date.getDate()+"-"+(date.getDate()+1)+"-"+date.getFullYear();
 let formData = new FormData();

     formData.set('date',date_);
    formData.set('amount',this.paymentFormGroup.value.amount);
    formData.set('type',this.paymentFormGroup.value.type);
    formData.set('studentCode',this.paymentFormGroup.value.studentCode);
    formData.set('file',this.paymentFormGroup.value.fileSource);
    this.studentsService.savePayments(formData).subscribe(
      value=>{
        this.showProgress=false;
        alert('Payment Saved successfully!')
      },
      error => {
        console.log(error)
      }
    )
}

  afterLoadComplete(event:any) {
    console.log(event);
  }
}
