import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../services/students.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrl: './payment-details.component.css'
})
export class PaymentDetailsComponent implements OnInit{
  paymentId! :number;
  pdfFileUrl!: any;
  constructor(private studentsService:StudentsService ,private  route:ActivatedRoute) {
  }
  ngOnInit() {
    this.paymentId=this.route.snapshot.params['id'];
    this.studentsService.getPaymentDetails(this.paymentId).subscribe(
      value=>{
let blob = new Blob( [value],{ type:'application/pdf'});
this.pdfFileUrl=window.URL.createObjectURL(blob);
      },err=>{
console.log(err);
      }
    )
  }

  afterLoadComplete(event: import("pdfjs-dist/types/src/display/api").PDFDocumentProxy) {

  }
}
